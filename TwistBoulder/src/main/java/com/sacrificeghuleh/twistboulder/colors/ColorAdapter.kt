package com.sacrificeghuleh.twistboulder.colors

import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sacrificeghuleh.twistboulder.R

class ColorAdapter(
    private var colors: List<ColorModel>
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    inner class ColorViewHolder(colorView: View) : RecyclerView.ViewHolder(colorView) {
        val checkbox: CheckBox
        val imageView: ImageView
        val imageViewBG: ImageView

        init {
            checkbox = colorView.findViewById(R.id.colorEnableCheckbox)
            imageView = colorView.findViewById(R.id.colorPreviewView)
            imageViewBG = colorView.findViewById(R.id.colorPreviewViewBG)
        }

        fun setSvgColor(colorFg: Color, colorBg: Color) {
            imageView.setColorFilter(colorFg.toArgb(), PorterDuff.Mode.SRC_IN)
            imageViewBG.setColorFilter(colorBg.toArgb(), PorterDuff.Mode.SRC_IN)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.color_recycler_view_row, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.apply {
            Log.d(
                this.javaClass.name,
                "Adding " + colors[position].name + " at position $position"
            )
            checkbox.text = colors[position].name

            if (position < colors.size - 1) {
                setSvgColor(colors[position].color, colors[position].colorAdjacent)
            } else {
                imageView.setImageResource(R.drawable.circlecolorful)
                imageViewBG.setImageResource(R.drawable.circlecolorful_bg)
            }

            checkbox.setOnClickListener {
                colors[position].enabled = checkbox.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}