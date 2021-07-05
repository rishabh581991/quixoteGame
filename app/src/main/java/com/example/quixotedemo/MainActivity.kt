package com.example.quixotedemo

import android.content.res.Configuration
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.quixotedemo.databinding.ActivityMainBinding

/**
 * @author Rishabh Jain
 * @version 1.0
 * @date 4th July 2021 (Sunday)
 */

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            Toast.makeText(this,"In Horizontal Mode",Toast.LENGTH_LONG).show()
            enableAllButtons(true)
        } else {
            // In portrait
            Toast.makeText(this,"In Vertical Mode",Toast.LENGTH_LONG).show()
            enableAllButtons(false)
            activityMainBinding.arrowImageView.rotation = 0.0f
        }

    }

    private fun enableAllButtons(buttonsEnable: Boolean) {
        activityMainBinding.apply {
            b1Button.isEnabled = buttonsEnable
            b2Button.isEnabled = buttonsEnable
            b3Button.isEnabled = buttonsEnable
            b4Button.isEnabled = buttonsEnable
        }
    }


}