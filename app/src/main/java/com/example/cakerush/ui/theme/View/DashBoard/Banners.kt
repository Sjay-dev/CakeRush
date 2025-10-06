package com.example.cakerush.ui.theme.View.DashBoard

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.cakerush.Model.SliderModel
import com.example.cakerush.R
import okhttp3.OkHttpClient

@Composable
fun Banners(banners: List<SliderModel>) {
    AutoSlidingCarousel(banners = banners)
}

@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier.padding(top = 16.dp),
    banners: List<SliderModel>,
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { banners.size }
    )
    val isDragged = pagerState.interactionSource.collectIsDraggedAsState()
    val context = LocalContext.current

    val okHttpClient = remember { OkHttpClient.Builder().build() }

    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                add(OkHttpNetworkFetcherFactory(callFactory = { okHttpClient }))
            }
            .build()
    }

    Column(modifier = modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .height(180.dp)
                .padding(horizontal = 16.dp)
        ) { page ->
            val imageUrl = banners[page].url
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .crossfade(true)
                .listener(
                    onSuccess = { _, _ ->
                        Log.d("Coil", "Loaded: $imageUrl")
                    },
                    onError = { _, throwable ->
                        Log.e("Coil", "Failed: $imageUrl $throwable")
                    }
                )
                .build()

            AsyncImage(
                model = request,
                imageLoader = imageLoader,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        DotIndicator(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            totalDots = banners.size,
            selectedIndex = pagerState.currentPage,
            dotSize = 8.dp
        )
    }
}

@Composable
fun DotIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = colorResource(R.color.darkBrown),
    unSelectedColor: Color = colorResource(R.color.grey),
    dotSize: Dp
) {
    LazyRow(modifier = modifier) {
        items(totalDots) { index ->
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(if (index == selectedIndex) selectedColor else unSelectedColor)
            )
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.width(4.dp))
            }
        }
    }
}