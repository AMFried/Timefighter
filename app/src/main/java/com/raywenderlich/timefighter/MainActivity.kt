package com.raywenderlich.timefighter

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity //According to developer.android.com, android.support packaged libraries are deprecated and succeeded by androidx
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private lateinit var gameScoreTextView: TextView
    private lateinit var timeLeftTextView: TextView
    private lateinit var tapMeButton: Button
    private var score = 0

    private var gameStarted = false

    private lateinit var countDownTimer: CountDownTimer
    private var initialCountDown: Long = 60000
    private var countDownInterval: Long = 1000
    private var timeLeft = 60

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameScoreTextView = findViewById(R.id.game_score_text_view) //Note to self: figure out what R stands for in the name of mnemonics, if this can't be done, just read it as [R]ead, or maybe [R]untime, since it's created on run...
        timeLeftTextView = findViewById(R.id.time_left_text_view)
        tapMeButton = findViewById(R.id.tap_me_button)

        tapMeButton.setOnClickListener{incrementScore()}
        // connect views to variables

        resetGame()
    }

    private fun incrementScore(){
        if(!gameStarted){
            startGame()
        }

        score++

        val newScore = getString(R.string.your_score, score) //Note to self update: It probably stands for Resource... It's not easy to contextually flow that way for me so I'm referring to it personally as Runtime...
        gameScoreTextView.text = newScore
    }

    private fun resetGame(){
        score = 0

        val initialScore = getString(R.string.your_score, score)
        gameScoreTextView.text = initialScore

        val initialTimeLeft = getString(R.string.time_left,60)
        timeLeftTextView.text = initialTimeLeft

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval){
            override fun onTick(millisUntilFinished: Long){
                timeLeft = millisUntilFinished.toInt() / 1000

                val timeLeftString = getString(R.string.time_left, timeLeft)
                timeLeftTextView.text = timeLeftString
            }

            override fun onFinish(){
                // To be Implemented Later
            }
        }
        gameStarted = false
    }

    private fun startGame(){
        countDownTimer.start()
        gameStarted = true
    }

    private fun endgame(){
        // end game logic
    }
}