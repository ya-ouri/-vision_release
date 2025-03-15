package com.ouri.vision

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class VisionDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = resources.getColor(R.color.cool_black, theme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vision_detail)

        val titleTextView: TextView = findViewById(R.id.detailTitle)
        val descriptionTextView: TextView = findViewById(R.id.detailDescription)
        val imageView: ImageView = findViewById(R.id.detailImage)
        val backButton: ImageButton = findViewById(R.id.backButton)

        val title = intent.getStringExtra("TITLE") ?: "Неизвестно"
        val description = intent.getStringExtra("DESCRIPTION") ?: "Описание отсутствует"
        val imageResId = getImageResIdForTitle(title)

        titleTextView.text = title
        descriptionTextView.text = description
        imageView.setImageResource(imageResId)

        backButton.setOnClickListener {
            onBackPressed()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) // Свайп влево
        }
    }

    private fun getImageResIdForTitle(title: String): Int {
        return when (title) {
            "Протанопия" -> R.drawable.protanopia_two
            "Дейтеранопия" -> R.drawable.deuteranopia_two
            "Тританопия" -> R.drawable.tritanopia_two
            "Ахроматопсия" -> R.drawable.achromatopsia
            "Зрение собак" -> R.drawable.dog
            "Зрение кошек" -> R.drawable.cat
            else -> R.drawable.protanopia_two
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right) // Свайп влево
    }
}