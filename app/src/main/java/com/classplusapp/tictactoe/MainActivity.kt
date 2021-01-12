package com.classplusapp.tictactoe

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Load logo as GIF
       Glide.with(this).load(R.drawable.tictactoe).into(logo)

        //Start Music
        var mediaPlayer = MediaPlayer.create(applicationContext, R.raw.back)
        mediaPlayer.start();

        //Quick Play
        quick.setOnClickListener(){
            val i = Intent(this, QuickPlayActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

        //Pass Play
        pass.setOnClickListener(){
            val i = Intent(this, PassPlayActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

        //Leaderboard
        leaderboard.setOnClickListener(){
            val i = Intent(this, LeaderBoardActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }

        //Settings
        settings.setOnClickListener(){
            val i = Intent(this, SettingsActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }

    override fun onBackPressed(){
        super.onBackPressed();
        finish()
        exitProcess(0)
    }
}