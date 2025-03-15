package com.ouri.vision

import android.animation.ObjectAnimator
import android.animation.Animator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import android.animation.PropertyValuesHolder

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var actionButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = resources.getColor(R.color.cool_black, theme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        actionButton = findViewById(R.id.actionButton)

        val onboardingItems = listOf(
            OnboardingItem(
                R.drawable.obn,
                "Добро пожаловать! Это приложение имитирует зрение животных и людей с дальтонизмом."
            ),
            OnboardingItem(
                R.drawable.onboarding_image_2,
                "Узнайте, как видят мир кошки и собаки с помощью уникальных фильтров."
            ),
            OnboardingItem(
                R.drawable.onboarding_image_3,
                "Применяйте фильтры, и смотрите на мир глазами дальтоника в реальном времени!"
            )
        )

        val adapter = OnboardingAdapter(onboardingItems)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        customizeTabLayout()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("OnboardingActivity", "Page selected: $position")
                if (position == onboardingItems.size - 1) {
                    actionButton.text = "Начать"
                } else {
                    actionButton.text = "Пропустить"
                }

                updateTabIndicators(position)
            }
        })

        actionButton.setOnClickListener {
            if (viewPager.currentItem == onboardingItems.size - 1) {
                val intent = Intent(this, ChoiceVisionActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, ChoiceVisionActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            Log.d("OnboardingActivity", "Initial tab indicator update")
            updateTabIndicators(0)
        }, 100)
    }

    private fun customizeTabLayout() {
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            tab?.let {
                val tabView = LayoutInflater.from(this)
                    .inflate(R.layout.custom_tab, null) as View
                it.customView = tabView
            }
        }
    }

    private fun updateTabIndicators(selectedPosition: Int) {
        for (i in 0 until tabLayout.tabCount) {
            val tab = tabLayout.getTabAt(i)
            val indicator = tab?.customView?.findViewById<View>(R.id.tabIndicator)

            if (indicator != null) {
                val animator = if (i == selectedPosition) {
                    ObjectAnimator.ofPropertyValuesHolder(
                        indicator,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, indicator.scaleX, 1.5f),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, indicator.scaleY, 1.5f)
                    ).apply {
                        duration = 300
                        addListener(object : Animator.AnimatorListener {
                            override fun onAnimationStart(animation: Animator) {}
                            override fun onAnimationEnd(animation: Animator) {
                                Log.d("OnboardingActivity", "Animation ended for tab $i")
                            }
                            override fun onAnimationCancel(animation: Animator) {}
                            override fun onAnimationRepeat(animation: Animator) {}
                        })
                    }
                } else {
                    ObjectAnimator.ofPropertyValuesHolder(
                        indicator,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, indicator.scaleX, 1f),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, indicator.scaleY, 1f)
                    ).apply {
                        duration = 300
                        addListener(object : Animator.AnimatorListener {
                            override fun onAnimationStart(animation: Animator) {}
                            override fun onAnimationEnd(animation: Animator) {
                                Log.d("OnboardingActivity", "Animation ended for tab $i")
                            }
                            override fun onAnimationCancel(animation: Animator) {}
                            override fun onAnimationRepeat(animation: Animator) {}
                        })
                    }
                }
                animator.start()
                indicator.invalidate()
            } else {
                Log.e("OnboardingActivity", "Indicator not found for tab $i")
            }
        }
    }
}