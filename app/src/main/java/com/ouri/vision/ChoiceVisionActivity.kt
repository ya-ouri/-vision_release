package com.ouri.vision

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import androidx.cardview.widget.CardView

class ChoiceVisionActivity : AppCompatActivity() {

    private var selectedButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = resources.getColor(R.color.cool_black, theme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_vision)


        val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        val isFirstLaunch = sharedPreferences.getBoolean("is_first_launch", true)

        if (isFirstLaunch) {

            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)


            sharedPreferences.edit().putBoolean("is_first_launch", false).apply()
            finish()
            return
        }


        findViewById<CardView>(R.id.protanopiaButton).setOnClickListener {
            applyFilter("protanopia")
        }

        findViewById<CardView>(R.id.deuteranopiaButton).setOnClickListener {
            applyFilter("deuteranopia")
        }

        findViewById<CardView>(R.id.tritanopiaButton).setOnClickListener {
            applyFilter("tritanopia")
        }

        findViewById<CardView>(R.id.achromatopsiaButton).setOnClickListener {
            applyFilter("achromatopsia")
        }

        findViewById<CardView>(R.id.dogButton).setOnClickListener {
            applyFilter("dog")
        }

        findViewById<CardView>(R.id.catButton).setOnClickListener {
            applyFilter("cat")
        }

        val visionButton: ImageButton = findViewById(R.id.visionButton)
        val infoButton: ImageButton = findViewById(R.id.infoButton)

        setButtonSelected(infoButton)

        visionButton.setOnClickListener {
            if (selectedButton != visionButton) {
                setButtonSelected(visionButton)
                val intent = Intent(this, VisionInfoActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                finish()
            }
        }

        infoButton.setOnClickListener {
            if (selectedButton != infoButton) {
                setButtonSelected(infoButton)
            }
        }
    }

    private fun applyFilter(filterType: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("FILTER_TYPE", filterType)
        startActivity(intent)
    }

    private fun setButtonSelected(button: ImageButton) {
        selectedButton?.setBackgroundResource(0)
        button.setBackgroundResource(R.drawable.selected_button_background)
        selectedButton = button
    }
}