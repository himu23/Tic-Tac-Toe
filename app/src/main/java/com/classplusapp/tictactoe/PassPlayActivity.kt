package com.classplusapp.tictactoe

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_pass_play.*

class PassPlayActivity : AppCompatActivity() {


    var activePlayer: Int = 1
    var gameState = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    var winningPositions = arrayOf(
            intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8), intArrayOf(2, 4, 6))
    var gameActive = true
    val list = mutableListOf<Int>()
    val ivList = mutableListOf<ImageView>()
    var a = ""
    var b = ""
    var delay: Long =0
    var p1Count: Int = 0
    var p2Count: Int = 0


    //Display X or O
    public fun display(v:View) {
        var iv: ImageView = v as ImageView
        var tagC = v.tag.toString().toInt()
        if(gameState[tagC]==0 && gameActive) {
            ivList.add(iv)
            list.add(tagC)

            gameState[tagC] = activePlayer

            if (activePlayer==1){
                iv.alpha = 1f
                iv.setImageResource(R.drawable.x1)  //Assign X image to ImageView
                activePlayer = 2
                textView2.text = "$b's Turn"
                textView2.setTextColor(Color.parseColor("#029efd"))

            }

            else {
                iv.alpha = 1f
                iv.setImageResource(R.drawable.o1) //Assign O image to ImageView
                activePlayer = 1
                textView2.text = "$a's Turn"
                textView2.setTextColor(Color.parseColor("#fe01bd"))
            }
        }


        //Check if anyone won
        for(winningPosition in winningPositions){

            if (gameState[winningPosition[0]]==gameState[winningPosition[1]]
                &&gameState[winningPosition[1]]==gameState[winningPosition[2]]
                &&gameState[winningPosition[1]]!=0&&gameActive){
                gameActive = false

                if(gameState[winningPosition[1]] == 1)
                {

                    //Removing winnig elements from visible elements
                    list.remove(winningPosition[0])
                    list.remove(winningPosition[1])
                    list.remove(winningPosition[2])

                    var arr1 = list.toIntArray()

                    var arr2 = intArrayOf(winningPosition[0], winningPosition[1], winningPosition[2])
                    var winner = 1
                    p1Count++
                    var message = "$a Wins"
                    animWin(arr1, arr2, winner)
                    end(message)
                }

                else {

                    list.remove(winningPosition[0])
                    list.remove(winningPosition[1])
                    list.remove(winningPosition[2])

                    var arr1 = list.toIntArray()
                    var arr2 = intArrayOf(winningPosition[0], winningPosition[1], winningPosition[2])
                    var winner = 2
                    p2Count++
                    var message = "$b Wins"
                    animWin(arr1, arr2, winner)
                    end(message)
                }

                break
            }

            else{
                 var gameOver = true
                for(i in gameState)
                {
                    if(i == 0)
                        gameOver = false
                }

                if(gameOver)
                {
                    var message = "It's a draw"
                    end(message)
                }
            }



        }


    }

    //Winning Animation
    fun animWin(a1: IntArray, b1: IntArray, winner: Int){

        var x = 0
        for(i in ivList){
            for(j in a1){
                if(i.getTag().toString().toInt() == j)
                {
                    i.alpha = 0.3f
                }
            }
        }

        for(i in ivList) {

            for (j in b1) {
                if (i.getTag().toString().toInt() == j) {
                    i.alpha = 0.3f

                    if(winner == 1) {
                        i.setImageResource(R.drawable.x2) //Add Winning X image
                    }
                    else {
                        i.setImageResource(R.drawable.o2) //Assign Winning Y Image
                    }

                    //Animate Winning Image
                    if(x==0)
                        i.animate().setDuration(600).setStartDelay(0).alpha(1f)
                    else if(x==1)
                        i.animate().setDuration(600).setStartDelay(200).alpha(1f)
                    else
                        i.animate().setDuration(600).setStartDelay(400).alpha(1f)
                    x++
                }
            }
        }

        //Board Animate to fade
        board0.animate().setDuration(600).alpha(0.3f)


    }

    //Starts game after collection of names
    fun onNext(view: View){
        for(i in 0 until cL.childCount)
        {
            var child: View = cL.getChildAt(i)
            child.isEnabled = true
        }
        board0.visibility = VISIBLE

        var v = arrayListOf<View>(p1,p2,bod1,bod2,next)

        for(i in v)
        {
            i.isEnabled = false
            i.visibility =   INVISIBLE
        }

        if(p1.text.toString()=="")
            a = "Player 1"
        else
            a = p1.text.toString()

        if(p2.text.toString()=="")
            b = "Player 2"
        else
            b = p2.text.toString()
        textView2.text = "$a's Turn"
        textView2.setTextColor(Color.parseColor("#fe01bd"))

        pnp.visibility = INVISIBLE
        textView2.visibility = VISIBLE

    }

    //Initializes the variables
    fun init(){

        gameState = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
        var v = arrayListOf<ImageView>(iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9)

        for(i in v){
            i.setImageDrawable(null)
        }

        play.isEnabled = false
        play.visibility = INVISIBLE
        textView.visibility = INVISIBLE
    }

    fun end(message:String){
        play.isEnabled = true
        play.visibility = VISIBLE
        textView.visibility = VISIBLE
        textView2.visibility = INVISIBLE
        textView.text = message
    }

    //Initializes variables that were changed during game
    fun playAgain(view: View){
        gameActive = true
        activePlayer = 1
        play.isEnabled = false
        play.visibility = INVISIBLE
        textView.visibility = INVISIBLE
        textView2.visibility = VISIBLE
        textView2.text = "$a's Turn"
        textView2.setTextColor(Color.parseColor("#fe01bd"))
        init()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_play)


        board0.visibility = INVISIBLE
        for(i in 0 until cL.childCount-7)
        {
            var child: View = cL.getChildAt(i)
            child.isEnabled = false
        }
        textView2.visibility = INVISIBLE
        init()


    }

    override fun onBackPressed(){
        super.onBackPressed();

        //SharedPreference to store number of wins and names of players
        var sp: SharedPreferences = getSharedPreferences("LeaderLog", Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sp.edit()

        if(sp.contains("$a"))
            p1Count += sp.getString("$a", "")!!.toInt()
        if(sp.contains("$b"))
            p2Count += sp.getString("$b", "")!!.toInt()

        if(a!="")
        editor.putString("$a", p1Count.toString())
        if(b!="")
        editor.putString("$b", p2Count.toString())
        editor.commit()
        finish()
        overridePendingTransition(0, 0)
    }

}