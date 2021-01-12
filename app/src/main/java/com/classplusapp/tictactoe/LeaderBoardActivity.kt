package com.classplusapp.tictactoe

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_leader_board.*
import java.lang.reflect.Array

class LeaderBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_board)

        // SharedPreferences to fetch leaderboard
        var sp: SharedPreferences = applicationContext.getSharedPreferences("LeaderLog", Context.MODE_PRIVATE)
        Log.i("ffffffffffffffff","${sp.all}")

        var iv1 = mutableListOf<TextView>(t11,t22,t33,t44,t55,t66,t77,t88,t99,t1010)
        var iv2 = arrayListOf<TextView>(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10)
        var k = ArrayList(sp.all.keys);
        var v = ArrayList(sp.all.values);


        var x = k.size-1
        for(i in 0 until k.size){
            for(j in 1 until x){
                if(v[j-1].toString().toInt()>v[j].toString().toInt()) {

                    var temp1 = v[j]
                    var temp2 = k[j]
                    v[j] = v[j+1]
                    v[j+1] = temp1

                    k[j] = k[j+1]
                    k[j+1] = temp2
                }
            }
        }

        var count: Int = 0

       for(i in iv1){
            if(count<k.size)
                i.text = k[count++]
           else
               break;
       }

        count = 0
        for(i in iv2){
            if(count<v.size)
                i.text = v[count++].toString()
            else
                break;
        }
    }


}