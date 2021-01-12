package com.classplusapp.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class SettingsActivity : AppCompatActivity() {


    var m = true
    var vi = true
    var v = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

    }

    override fun onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(0, 0)
    }

    public fun backFun(view:View){
        onBackPressed()
    }

    /*
    THE FUNCTIONS MUSIC, VIBRATION AND VOLUME TOGGLE THE SAME, CURRENTLY THEY ONLY REDUCES INTESINTY
        OF THE ICONS AND CHANGES IMAGE RESOURCE*/

    /*COMPLETE FUNCTIONALITY TO BE ADDED*/


    public fun music(view: View){

        var iv: ImageView = view as ImageView

        if(m){
            iv.alpha = 0.5f
            iv.setImageResource(R.drawable.music1)
            m = false
        }

        else{
            iv.alpha = 1f
            iv.setImageResource(R.drawable.music)
            m = true
        }
    }

    public fun vibration(view: View){

        var iv: ImageView = view as ImageView

        if(vi){
            iv.alpha = 0.5f
            iv.setImageResource(R.drawable.vib1)
            vi = false
        }

        else{
            iv.alpha = 1f
            iv.setImageResource(R.drawable.vib)
            vi = true
        }

    }

    public fun volume(view: View){

        var iv: ImageView = view as ImageView

        if(v){
            iv.alpha = 0.5f
            iv.setImageResource(R.drawable.vol1)
            v = false
        }

        else{
            iv.alpha = 1f
            iv.setImageResource(R.drawable.vol)
            v = true
        }

    }

}