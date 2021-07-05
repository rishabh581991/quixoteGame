package com.example.quixotedemo

import android.content.res.Configuration
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.quixotedemo.databinding.ActivityMainBinding

/**
 * @author Rishabh Jain
 * @version 1.0
 * @date 5th July 2021 (Monday)
 */

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var doubleClickTimeDelta : Long = 300
    private var lastClickTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            Toast.makeText(this,"In Horizontal Mode",Toast.LENGTH_LONG).show()
            enableAllButtons(true)

            // rotate the center arrow in clockwise
            rotateArrowClockWise(binding.arrowImageView)

            // button press
            binding.apply {
                setButtonPressCondition(b1Button,-45f)
                setButtonPressCondition(b2Button,45f)
                setButtonPressCondition(b3Button,135f)
                setButtonPressCondition(b4Button,225f)
            }

        } else {
            // In portrait
            Toast.makeText(this,"In Vertical Mode",Toast.LENGTH_LONG).show()
            enableAllButtons(false)
            binding.arrowImageView.rotation = 0.0f
        }

    }

    private fun setButtonPressCondition(button : ImageButton, location : Float) {

            button.setOnTouchListener(View.OnTouchListener { v, event ->
               when(event.action){
                   MotionEvent.ACTION_DOWN -> {
                       val clickTime = System.currentTimeMillis()
                       if (clickTime - lastClickTime < doubleClickTimeDelta){
                           // When button is pressed twice but not released ->  the arrow should point
                           // exactly opposite to this button until the button is released.
                           binding.arrowImageView.clearAnimation()
                           binding.arrowImageView.rotation = (location + 180)
                       } else {
                           // Single Button Press but not released -> The arrow should point to that button until the button is released
                           binding.arrowImageView.clearAnimation()
                           binding.arrowImageView.rotation = location
                       }
                       lastClickTime = clickTime;
                   }
                   MotionEvent.ACTION_UP -> {
                       rotateArrowClockWise(binding.arrowImageView)
                   }
               }
                return@OnTouchListener true
            })

    }

    private fun rotateArrowClockWise(imageView: ImageView) {
        val rotateAnimation = RotateAnimation(0f,360f, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = 1000;
        rotateAnimation.repeatCount = Animation.INFINITE;
        imageView.startAnimation(rotateAnimation);
    }

    private fun enableAllButtons(buttonsEnable: Boolean) {
        binding.apply {
            b1Button.isEnabled = buttonsEnable
            b2Button.isEnabled = buttonsEnable
            b3Button.isEnabled = buttonsEnable
            b4Button.isEnabled = buttonsEnable
        }
    }


}