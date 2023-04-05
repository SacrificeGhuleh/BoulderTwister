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

        init {
            checkbox = colorView.findViewById(R.id.colorEnableCheckbox)
            imageView = colorView.findViewById(R.id.colorPreviewView)
        }

        fun setSvgColor(color: Color) =
            imageView.setColorFilter(color.toArgb(), PorterDuff.Mode.SRC_IN)
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
                "ColorAdapter",
                "Adding " + colors[position].name + " at position $position"
            )
            checkbox.text = colors[position].name
            if (position < colors.size - 1) {
                setSvgColor(colors[position].color)
            } else {
                imageView.setImageResource(R.drawable.circlecolorful)
            }

            checkbox.setOnClickListener {
                colors[position].enabled = checkbox.isEnabled
            }
        }
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}