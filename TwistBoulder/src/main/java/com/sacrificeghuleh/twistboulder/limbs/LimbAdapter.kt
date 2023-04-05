package com.sacrificeghuleh.twistboulder.limbs

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.sacrificeghuleh.twistboulder.R

class LimbAdapter(
    private var limbs: List<LimbModel>
) : RecyclerView.Adapter<LimbAdapter.LimbViewHolder>() {

    init {
        Log.d("LimbAdapter", "Initialization, limbs: $limbs")
    }

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
            Log.d(
                "ColorAdapter",
                "Adding " + limbs[position].name + " at position $position"
            )
            checkbox.text = limbs[position].name
            checkbox.setOnClickListener {
                limbs[position].enabled = checkbox.isEnabled
            }
            setIsRecyclable(true)
        }
    }

    override fun getItemCount(): Int {
        return limbs.size
    }
}