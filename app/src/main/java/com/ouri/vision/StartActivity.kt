package com.ouri.vision

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // Проверка первого запуска с помощью SharedPreferences
        val sharedPreferences = getSharedPreferences("app_preferences", MODE_PRIVATE)
        val isFirstLaunch = sharedPreferences.getBoolean("is_first_launch", true)

        if (isFirstLaunch) {
            // Первый запуск: показываем OnboardingActivity
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)

            // Отмечаем, что пользователь видел Onboarding
            sharedPreferences.edit().putBoolean("is_first_launch", false).apply()
            finish()
        } else {
            // Не первый запуск: показываем текущий экран или переходим к MainActivity
            val startButton: Button = findViewById(R.id.startButton)
            startButton.setOnClickListener {
                val intent = Intent(this, ChoiceVisionActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
