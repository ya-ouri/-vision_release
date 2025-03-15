package com.ouri.vision

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageButton

class VisionInfoActivity : AppCompatActivity() {

    private var selectedButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = resources.getColor(R.color.cool_black, theme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vision_info)

        val recyclerView: RecyclerView = findViewById(R.id.visionRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val visionTypes = listOf(
            VisionType(
                "Протанопия",
                "Неспособность различать красный цвет.",
                "Протанопия — это вид дальтонизма, при котором человек не может нормально видеть красный цвет. Вместо красного он может видеть серый или зеленый, особенно если цвета рядом. Например, красный светофор может сливаться с зеленым. Это бывает с рождения, и примерно 1 из 100 человек сталкивается с такой особенностью.",
                R.drawable.protanopia_two
            ),
            VisionType(
                "Дейтеранопия",
                "Неспособность различать зеленый цвет.",
                "Дейтеранопия — это вид дальтонизма, при котором человеку трудно различать зеленый цвет. Человек с дейтеранопией видит зеленый как серый или желтый, и это может путать, особенно если рядом есть красный или оранжевый. Например, в лесу листья могут казаться просто темными пятнами, а на светофоре зеленый может сливаться с другими цветами. Это врожденный дефект  и встречается чуть чаще, чем протанопия, примерно у 1-2% людей, в основном мужчин. ",
                R.drawable.deuteranopia_two
            ),
            VisionType(
                "Тританопия",
                "Неспособность различать синий и желтый.",
                "Тританопия — это редкий  вид дальтонизма, при котором человек не различает синий и желтый цвета. Из-за этого синий может казаться зеленым, а желтый — серым или бледным. \n" +
                        "Человек с тританопией может не сразу понять, где заканчивается небо, если оно сливается с землей. Это врожденный дефект и встречается примерно у 1 из 10 000 человек.",
                R.drawable.tritanopia_two
            ),
            VisionType(
                "Ахроматопсия",
                "Полное отсутствие цветового зрения.",
                "Ахроматопсия — это вид дальтонизма, при котором человек вообще не видит цвета. Это самая редкая и сложная форма дальтонизма, встречается примерно у 1 из 30 000 человек. Мир для таких людей — это разные оттенки серого: от очень светлого до очень темного. К тому же, глаза у людей с таким дефектом часто чувствительны к яркому свету, и зрение может быть не очень острым.",
                R.drawable.achromatopsia
            ),
            VisionType(
                "Зрение собак",
                "Ограниченное цветовое восприятие.",
                "Собаки видят мир не так, как мы. У них нет такого же яркого восприятия цветов, как у людей. Cобаки хорошо видят синие, желтые и фиолетовые цвета, а красный и зеленый они будет видеть в серых оттенках. Ночью они видят окружающий мир в среднем также как человек во время сумерек и способны распознавать около 40 серых оттенков. Также собаки плохо видят вблизи. Более чёткое изображение появляется, если объект удалён на 100–150 метров, а движущиеся образы хорошо видимы с расстояния 300–400 метров.",
                R.drawable.dog
            ),
            VisionType(
                "Зрение кошек",
                "Акцент на движение в темноте.",
                "Кошки видят цвета более тусклыми. Они различают синий и зеленый, черно-белую гамму и распознают огромное количество оттенков серого цвета, около 26, но теплые тона, такие как - красный, коричневый, желтый и оранжевый для них почти не видны. Они хорошо видят объекты на расстоянии, а то, что находится в непосредственной близости, выглядит размыто. ",
                R.drawable.cat
            )
        )

        val adapter = VisionInfoAdapter(visionTypes) { visionType ->
            val intent = Intent(this, VisionDetailActivity::class.java).apply {
                putExtra("TITLE", visionType.title)
                putExtra("DESCRIPTION", visionType.fullDescription)
            }
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
        recyclerView.adapter = adapter

        val visionButton: ImageButton = findViewById(R.id.visionButton)
        val infoButton: ImageButton = findViewById(R.id.infoButton)

        // Устанавливаем начальное выделение для visionButton (текущая страница)
        setButtonSelected(visionButton)

        visionButton.setOnClickListener {
            if (selectedButton != visionButton) {
                setButtonSelected(visionButton)
                // Уже находимся на VisionInfoActivity, ничего не делаем
            }
        }

        infoButton.setOnClickListener {
            if (selectedButton != infoButton) {
                setButtonSelected(infoButton)
                val intent = Intent(this, ChoiceVisionActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                finish()
            }
        }
    }

    private fun setButtonSelected(button: ImageButton) {
        // Удаляем выделение с предыдущей кнопки
        selectedButton?.setBackgroundResource(0)
        // Устанавливаем выделение на новую кнопку
        button.setBackgroundResource(R.drawable.selected_button_background)
        selectedButton = button
    }
}