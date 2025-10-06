package com.example.cakerush.ui.theme.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cakerush.ui.theme.CakeRushTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CakeRushTheme {

                }
            }
        }
    }



@Composable
fun DashBoardScreen(onCartClick: () -> Unit) {
    val viewModel = CakeRushViewModel()

    val banners = remember { mutableStateListOf<SliderModel>() }
    val categories = remember { mutableStateListOf<CategoryModel>() }
    val bestSeller = remember { mutableStateListOf<ItemsModel>() }

    var showBannerLoading by remember { mutableStateOf(true) }
    var showCategoryLoading by remember { mutableStateOf(true) }
    var showBestSellerLoading by remember { mutableStateOf(true) }


//For Banner
    LaunchedEffect(Unit) {
        viewModel.loadBanner().observeForever {it
            banners.clear()
            banners.addAll(it)
            showBannerLoading = false
        }
    }

    //For Category
    LaunchedEffect(Unit) {
        viewModel.loadCategory().observeForever {it
            categories.clear()
            categories.addAll(it)
            showCategoryLoading = false
        }
    }

    //For Best Selling Product
    LaunchedEffect(Unit) {
        viewModel.loadBestSeller().observeForever {it
            bestSeller.clear()
            bestSeller.addAll(it)
            showBestSellerLoading = false
        }
    }


    ConstraintLayout(modifier = Modifier.background(Color.White))
    { val (scrollList, bottomMenu) = createRefs()

        LazyColumn(modifier = Modifier.fillMaxSize()
            .constrainAs(scrollList) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
        ) {
            item{
                Row(
                    modifier = Modifier.fillMaxSize()
                        .padding(top = 70.dp).padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column {
                        Text("Welcome Back" , color = Color.Black)
                        Text("Joseph" , color = Color.Black
                            , fontSize = 18.sp, fontWeight = Bold
                        )
                    }

                    Row(){
                        Image(painter = painterResource(R.drawable.search_icon) , contentDescription = null)

                        Spacer(modifier = Modifier.width(16.dp))

                        Image(painter = painterResource(R.drawable.bell_icon) , contentDescription = null)

                    }

                }
            }
            item{
                if (showBannerLoading){
                    Box(modifier = Modifier.fillMaxSize()
                        .height(200.dp) ,
                        contentAlignment = Alignment.Center)
                    {
                        CircularProgressIndicator()
                    }
                }
                else{
                    Banners(banners = banners)
                }

            }

            item {
                Text("Categories" ,
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = Bold,
                    modifier = Modifier.padding(top = 24.dp)
                        .padding(horizontal = 16.dp)
                )
            }

            item {
                if(showCategoryLoading){
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator( )
                    }
                }
                else{
                    CategoryList(categories = categories)
                }
            }

            item{
                Row (
                    modifier = Modifier.fillMaxWidth()
                        .padding(top= 24.dp)
                        .padding(horizontal = 16.dp) ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text("Best Selling Products" ,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = Bold
                    )
                    Text("See All" ,
                        color =  colorResource(R.color.midBrown)
                    )
                }
            }

            item {
                if (showBestSellerLoading){
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ){
                        CircularProgressIndicator()
                    }
                }
                else{
                    ListItems(items = bestSeller)
                }
            }
        }

        BottomMenu(modifier = Modifier.fillMaxWidth()
            .constrainAs(bottomMenu){ bottom.linkTo(parent.bottom) }
            , onItemClick = onCartClick
        )

    }

}