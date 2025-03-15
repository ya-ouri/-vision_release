package com.ouri.vision

data class VisionType(
    val title: String,
    val shortDescription: String,
    val fullDescription: String,
    val imageResId: Int // Добавлен параметр для изображения
)