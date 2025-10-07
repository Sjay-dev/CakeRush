package com.example.cakerush.ui.theme.View.Cart

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cakerush.R
import com.example.cakerush.ui.theme.Helper.ManagmentCart
import com.example.cakerush.ui.theme.View.BaseActivity

class CartActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CartScreen(ManagmentCart(this), onBackClick = {finish()})

        }


    }

    @Composable
    fun CartScreen(managmentCart:ManagmentCart = ManagmentCart(LocalContext.current)
                   , onBackClick: () -> Unit
    ) {
        var cartItem = remember { mutableStateOf(managmentCart.getListCart()) }

        val  tax = remember { mutableStateOf(0.0) }

        calculatorCart(managmentCart , tax)

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
        ){
            ConstraintLayout(modifier = Modifier.padding(top = 36.dp)) {
                val (backBtn, cartTxt) = createRefs()

                Text(modifier = Modifier.fillMaxWidth()
                    .constrainAs(cartTxt) { centerTo(parent) } ,
                    text = "Your Cart" ,
                    textAlign = TextAlign.Center,
                    fontWeight = Bold,
                    fontSize = 25.sp
                )

                Image(painter = painterResource(R.drawable.back),
                    contentDescription = null,
                    modifier = Modifier.constrainAs (backBtn){
                        top.linkTo(parent.top)
                        bottom.linkTo((parent.bottom))
                        start.linkTo(parent.start)
                    }.clickable{
                        onBackClick()
                    }
                )
            }

            if(cartItem.value.isEmpty()){
                Text("Cart is Empty" , modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            else{
                CartList(cartItems= cartItem.value , managmentCart){
                    cartItem.value = managmentCart.getListCart()
                    calculatorCart(managmentCart , tax)
                }

                CartSummary(
                    itemTotal = managmentCart.totalFee,
                    tax = tax.value,
                    delivery = 10.0
                )

            }
        }
    }

    fun calculatorCart(
        managmentcart: ManagmentCart,
        tax: MutableState<Double>
    ) {
        val perecentTax = 0.02
        tax.value = Math.round((managmentcart.totalFee*perecentTax)*100)/100.0
    }
}