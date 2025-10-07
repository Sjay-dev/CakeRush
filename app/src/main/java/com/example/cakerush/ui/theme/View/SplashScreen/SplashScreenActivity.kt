package com.example.cakerush.ui.theme.View.SplashScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cakerush.R
import com.example.cakerush.ui.theme.View.BaseActivity
import com.example.cakerush.ui.theme.View.DashBoard.MainActivity

class SplashScreenActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen(onClick = {
                startActivity(Intent(this, MainActivity::class.java))
            })
        }
    }
}

@Composable
@Preview
fun SplashScreen(onClick: () -> Unit = {})
{

    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = colorResource(R.color.Brown))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.splash_logo) , contentDescription = "Splash Logo"
            , modifier = Modifier.padding(top = 48.dp)
                .fillMaxSize()
            , contentScale = ContentScale.Fit
        )

        Text(text = "Satisfy your Cravings with our\n" +
                "Fresh Cakes, Donuts\n" +
                "and more Pastries"

            , color = colorResource(R.color.darkBrown),
            textAlign = TextAlign.Center, fontSize = 26.sp ,
            fontWeight = SemiBold , lineHeight = 40.sp ,
            modifier = Modifier.padding(top= 16.dp)
        )

        Text(text = "Browse your best edibles from top Seller\n" +
                "Get personalized recommendations\n" +
                "Enjoy fast, free shipping"

            , color = colorResource(R.color.darkBrown),
            textAlign = TextAlign.Center, fontSize = 16.sp ,
            lineHeight = 30.sp ,
            modifier = Modifier.padding(top= 16.dp)
        )

        Button(onClick = {onClick()}
            , shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.green))
            , modifier = Modifier.padding(top = 16.dp)
                .fillMaxWidth()
                .height(50.dp)
        )
        {
            Text(text = "Let's Get Started", color = colorResource(R.color.white)
                , fontSize = 18.sp
            )

        }

        Text(text = "Already have an account? Sign in"
            , color = colorResource(R.color.darkBrown),
            textAlign = TextAlign.Center, fontSize = 18.sp ,
            lineHeight = 30.sp,
            modifier = Modifier.padding(top= 16.dp)
        )

    }
}