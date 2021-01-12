package com.classplusapp.tictactoe

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_quick_play.board0
import kotlinx.android.synthetic.main.activity_quick_play.iv1
import kotlinx.android.synthetic.main.activity_quick_play.iv2
import kotlinx.android.synthetic.main.activity_quick_play.iv3
import kotlinx.android.synthetic.main.activity_quick_play.iv4
import kotlinx.android.synthetic.main.activity_quick_play.iv5
import kotlinx.android.synthetic.main.activity_quick_play.iv6
import kotlinx.android.synthetic.main.activity_quick_play.iv7
import kotlinx.android.synthetic.main.activity_quick_play.iv8
import kotlinx.android.synthetic.main.activity_quick_play.iv9
import kotlinx.android.synthetic.main.activity_quick_play.play
import kotlinx.android.synthetic.main.activity_quick_play.textView

class QuickPlayActivity : AppCompatActivity() {


    var gameState = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0) //Stores gamestate i.e. 0 = Inatcive, 1 = Bot, 2 = Player
    var winningPositions = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6))
    var gameActive = true
    var activePlayer = 1
    val list = mutableListOf<Int>()
    val ivList = mutableListOf<ImageView>()
    var tagList = mutableListOf<Int>()
    var al = arrayListOf<ImageView>()

    //Simulates a computer bot
    fun bot() {

        /* This function assigns image to random legal position*/

        var random = al.random()
        var r: ImageView = random
        al.remove(random)
        if(gameActive) {
            gameState[random.tag.toString().toInt()] = activePlayer
            r.setImageResource(R.drawable.x1)
            r.animate().setDuration(400).setStartDelay(50).alpha(1f)
            activePlayer = 2
            ivList.add(r)
            list.add(random.tag.toString().toInt())
        }
            check()
    }

    //Displays moves made by user
    fun display(v:View) {
        var iv: ImageView = v as ImageView
        var tagC = v.tag.toString().toInt()


        if(gameState[tagC]==0 && gameActive) {
            ivList.add(iv)
            list.add(tagC)

            gameState[tagC] = activePlayer


            if(activePlayer==1){
                bot() //The control is passed to bot for next move
            }
            else{

                iv.setImageResource(R.drawable.o1)
                iv.animate().setDuration(400).setStartDelay(50).alpha(1f)
                activePlayer = 1
                al.remove(v)
                check()
                bot()
            }

        }

    }

    //Set certain constraints on completion of game
    fun end(message:String){
        play.isEnabled = true
        play.visibility = VISIBLE
        textView.visibility = VISIBLE
        textView.text = message
    }

    //Check for winning postions
    fun check(){
        for(winningPosition in winningPositions){

            if (gameState[winningPosition[0]]==gameState[winningPosition[1]]
                &&gameState[winningPosition[1]]==gameState[winningPosition[2]]
                &&gameState[winningPosition[1]]!=0&&gameActive){
                gameActive = false

                if(gameState[winningPosition[1]] == 1)
                {

                    list.remove(winningPosition[0])
                    list.remove(winningPosition[1])
                    list.remove(winningPosition[2])
                    var winner = 1
                    var arr1 = list.toIntArray()
                    var arr2 = intArrayOf(winningPosition[0], winningPosition[1], winningPosition[2])
                    var message = "You Lost!"
                    animWin(arr1, arr2, winner)
                    end(message)
                }

                else if(gameState[winningPosition[1]] == 2){

                    list.remove(winningPosition[0])
                    list.remove(winningPosition[1])
                    list.remove(winningPosition[2])
                    var winner = 2
                    var arr1 = list.toIntArray()
                    var arr2 = intArrayOf(winningPosition[0], winningPosition[1], winningPosition[2])
                    var message = "You won!"
                    animWin(arr1, arr2, winner)
                    end(message)
                }

                break
            }

            else{
                var gameOver = true
                for(i in gameState)
                {
                    if(i == 0) {
                        gameOver = false
                        break
                    }
                }

                if(gameOver)
                {
                    var message = "It's a draw"
                    end(message)
                }

            }
        }
    }


    //Animation Winning/Losing
    fun animWin(a1: IntArray, b1: IntArray, winner: Int) {

        var x = 0
        for (i in ivList) {
            for (j in a1) {
                if (i.getTag().toString().toInt() == j) {
                    i.animate().setDuration(400).setStartDelay(50).alpha(0.3f)
                }
            }
        }

        for (i in ivList) {

            for (j in b1) {
                if (i.getTag().toString().toInt() == j) {
                    i.animate().setDuration(400).setStartDelay(50).alpha(0.3f)

                    if (winner != 1) {
                        i.setImageResource(R.drawable.o2)
                    }
                    if (x == 0)
                        i.animate().setDuration(600).setStartDelay(0).alpha(1f)
                    else if (x == 1)
                        i.animate().setDuration(600).setStartDelay(200).alpha(1f)
                    else
                        i.animate().setDuration(600).setStartDelay(400).alpha(1f)
                    x++
                }
            }
        }

        board0.animate().setDuration(600).alpha(0.3f)
    }

    //Initialize variables on each play
    fun init(){

        gameState = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
         var v = arrayListOf<ImageView>(iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9)

        for(i in v){
            i.setImageDrawable(null)
      }
        al.clear()
        al.add(iv1)
        al.add(iv2)
        al.add(iv3)
        al.add(iv4)
        al.add(iv5)
        al.add(iv6)
        al.add(iv7)
        al.add(iv8)
        al.add(iv9)
        ivList.clear()
        list.clear()
        play.isEnabled = false
        play.visibility = INVISIBLE
        textView.visibility = INVISIBLE
        bot()
    }


    //Initializes certain variables after first play
    fun playAgain(view: View){
        gameActive = true
        activePlayer = 1
        play.isEnabled = false
        play.visibility = INVISIBLE
        textView.visibility = INVISIBLE
        init()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_play)
        var view = arrayListOf<View>(iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9)
        init()
    }
    override fun onBackPressed(){
        super.onBackPressed();
        finish()
        overridePendingTransition(0, 0)
    }

}