package com.example.cakerush.ui.theme.View.Detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.cakerush.Model.ItemsModel
import com.example.cakerush.R

@Composable
fun InfoSection(item: ItemsModel){
    Column(modifier = Modifier.padding(horizontal = 16.dp , vertical = 8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = item.title,
                fontSize = 23.sp, fontWeight = Bold,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "$${item.price}",
                fontWeight = Bold,
                fontSize = 22.sp,
            )
        }
        Text(
            text = "Seller",
            fontWeight = Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = item.sellerPic,
                contentDescription = "Seller Pic",
                modifier = Modifier.size(60.dp)
            )

            Text(
                text = "Jemmy Hanks",
                fontWeight = Bold,
                modifier = Modifier.padding(start = 16.dp).weight(1f)
            )

            Row {
                Image(
                    painter = painterResource(R.drawable.message),
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Image(painter = painterResource(R.drawable.call), contentDescription = null)
            }
        }
    }
}