package eu.kenway.ecommerce.SplashScreen

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.RequiresApi
import eu.kenway.ecommerce.MainActivity
import eu.kenway.ecommerce.R
import eu.kenway.ecommerce.databinding.ActivityMainBinding
import eu.kenway.ecommerce.databinding.ActivitySplashScreenBinding
import java.util.*

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
     private val splashscreen_time=3500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding= ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }



        Handler(Looper.getMainLooper()).postDelayed({
              startActivity(Intent(this,MainActivity::class.java))
        }, 3000) //millis
    }
}