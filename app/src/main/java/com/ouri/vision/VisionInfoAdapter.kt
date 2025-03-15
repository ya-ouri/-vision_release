package com.ouri.vision

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent

class VisionInfoAdapter(
    private val visionTypes: List<VisionType>,
    private val onClick: (VisionType) -> Unit
) : RecyclerView.Adapter<VisionInfoAdapter.VisionViewHolder>() {

    inner class VisionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.visionImage)
        val title: TextView = itemView.findViewById(R.id.visionTitle)
        val description: TextView = itemView.findViewById(R.id.visionDescription)

        fun bind(visionType: VisionType) {
            imageView.setImageResource(visionType.imageResId)
            title.text = visionType.title
            description.text = visionType.shortDescription
            itemView.setOnClickListener { onClick(visionType) } // Клик по всей карточке
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vision_info, parent, false)
        return VisionViewHolder(view)
    }

    override fun onBindViewHolder(holder: VisionViewHolder, position: Int) {
        holder.bind(visionTypes[position])
    }

    override fun getItemCount(): Int = visionTypes.size
}