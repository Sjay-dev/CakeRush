package com.example.cakerush.ui.theme.View.Cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cakerush.R

@Composable
fun CartSummary(itemTotal: Double , tax: Double, delivery: Double){
    val total = itemTotal + tax + delivery

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()
            .padding(top = 16.dp)) {

            Text(text = "Item Total:" , modifier = Modifier.weight(1f),
                fontWeight = Bold,
                color = colorResource(R.color.black)
            )
            Text(text = "$$itemTotal" , fontWeight = Bold)
        }

        Row(modifier = Modifier.fillMaxWidth()
            .padding(top = 16.dp)) {

            Text(text = "Tax:" , modifier = Modifier.weight(1f),
                fontWeight = Bold,
                color = colorResource(R.color.black)
            )
            Text(text = "$$tax" , fontWeight = Bold)
        }

        Row(modifier = Modifier.fillMaxWidth()
            .padding(top = 16.dp)) {

            Text(text = "Delivery:" , modifier = Modifier.weight(1f),
                fontWeight = Bold,
                color = colorResource(R.color.black)
            )
            Text(text = "$$delivery" , fontWeight = Bold)
        }

        Box(modifier = Modifier.padding(top = 16.dp)
            .height(1.dp)
            .fillMaxWidth()
            .background(colorResource(R.color.grey))
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 16.dp)
        ){
            Text(text = "Total:" , modifier = Modifier.weight(1f),
                fontWeight = Bold,
                color = colorResource(R.color.black)
            )
            Text(text = "$$total" , fontWeight = Bold)
        }

        Button(onClick = {},
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.green)
            ),
            modifier = Modifier.fillMaxWidth()
                .padding(top = 32.dp)
                .height(50.dp)
        ) {
            Text(text = "Proceed To Checkout" , fontSize = 18.sp,
                color = Color.White)

        }
    }
}