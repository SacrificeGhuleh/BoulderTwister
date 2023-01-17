package com.sacrificeghuleh.twistboulder

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(
    var colors: List<ColorModel>
) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    inner class ColorViewHolder(colorView: View) : RecyclerView.ViewHolder(colorView) {
        public val checkbox: CheckBox
        public val imageView: ImageView

        init {
            checkbox = colorView.findViewById(R.id.colorEnableCheckbox)
            imageView = colorView.findViewById(R.id.colorPreviewView)
        }

        public fun setSvgColor(color: Color) =
            imageView.setColorFilter(color.toArgb(), PorterDuff.Mode.SRC_IN);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.color_recycler_view_row, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.apply {
            checkbox.text = colors[position].name
            setSvgColor(colors[position].color)
        }
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}