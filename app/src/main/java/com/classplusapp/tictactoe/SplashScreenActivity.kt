package com.classplusapp.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //Load logo as GIF
        Glide.with(this).load(R.drawable.tictactoe).into(logo0)

        //Initialize logo as invisible
        logo0.alpha = 0f

        //Array of winner all the ImageViews
        val winner = arrayOf<ImageView>(g3, g2, g1)
        val other = arrayOf<ImageView>(b1, b2, b3, r1, board0)

        var delay: Long = 300

        //Initialize winner ImageViews as Invisible
        for(i in winner){
            i.alpha = 0f
        }

        //Initialize other ImageViews as Visible
        for(i in other){
            i.alpha = 1f
        }

        //Animation to reduce the intensity of other ImageViews
        for(i in other){
            i.animate().setDuration(1200).alpha(0.3f)
        }

        //Animation to increase the intensity of winner ImageViews
        for(i in winner){
            i.animate().setDuration(300).setStartDelay(delay).alpha(1f)
            delay+=300
        }

        //Animation to increase the intensity of Logo and call MainActivity
        logo0.animate().setDuration(1500).alpha(1f).withEndAction(){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }

    }
}