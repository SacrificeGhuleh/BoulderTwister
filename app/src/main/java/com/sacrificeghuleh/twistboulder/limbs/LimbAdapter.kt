package com.sacrificeghuleh.twistboulder.limbs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.sacrificeghuleh.twistboulder.R

class LimbAdapter(
    private var limbs: List<LimbModel>
) : RecyclerView.Adapter<LimbAdapter.LimbViewHolder>() {

    inner class LimbViewHolder(LimbView: View) : RecyclerView.ViewHolder(LimbView) {
        val checkbox: CheckBox

        init {
            checkbox = LimbView.findViewById(R.id.limbEnableCheckbox)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LimbAdapter.LimbViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.limb_recycler_view_row, parent, false)
        return LimbViewHolder(view)
    }

    override fun onBindViewHolder(holder: LimbAdapter.LimbViewHolder, position: Int) {
        holder.apply {
            checkbox.text = limbs[position].name
        }
    }

    override fun getItemCount(): Int {
        return limbs.size
    }
}