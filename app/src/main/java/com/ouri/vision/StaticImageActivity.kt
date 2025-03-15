package com.ouri.vision

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.co.cyberagent.android.gpuimage.GPUImageView
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorMatrixFilter

class StaticImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static_image)

        val gpuImageView: GPUImageView = findViewById(R.id.gpuImageView)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.example_1)
        val grayscaleFilter = GPUImageColorMatrixFilter(
            1.0f,
            floatArrayOf(
                0.38f, 0.68f, -0.10f, 0.0f,
                0.30f, 0.52f, 0.08f, 0.0f,
                -0.01f, 0.02f, 0.90f, 0.0f,
                0.0f, 0.0f, 0.0f, 1.0f
            )
        )
        gpuImageView.setFilter(grayscaleFilter)
        gpuImageView.setImage(bitmap)
    }
}

//                0.33f, 0.33f, 0.33f, 0.0f,
//                0.33f, 0.33f, 0.33f, 0.0f,
//                0.33f, 0.33f, 0.33f, 0.0f,
//                0.0f,  0.0f,  0.0f,  1.0f

//                0.95f, 0.05f, 0.00f, 0.0f,
//                0.00f, 0.90f, 0.10f, 0.0f,
//                0.20f, 0.70f, 0.10f, 0.0f,
//                0.0f,  0.0f,  0.0f,  1.0f