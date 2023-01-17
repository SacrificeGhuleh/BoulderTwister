package com.sacrificeghuleh.twistboulder

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sacrificeghuleh.twistboulder.colors.ColorAdapter
import com.sacrificeghuleh.twistboulder.colors.ColorModel
import com.sacrificeghuleh.twistboulder.limbs.LimbAdapter
import com.sacrificeghuleh.twistboulder.limbs.LimbModel

class MainActivity : AppCompatActivity() {
    private var colorModels = ArrayList<ColorModel>()
    private var limbModels = ArrayList<LimbModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpColors()
        setUpLimbs()

        addColorAdapter()
        addLimbsAdapter()
    }

    private fun setUpColors() {
        val colNames = resources.getStringArray(R.array.boulder_colors_names) //Array<String>
        val colCodes = resources.getIntArray(R.array.boulder_colors) // IntArray
        colCodes.zip(colNames) { colCode, colName ->
            colorModels.add(ColorModel(colName, Color.valueOf(colCode)))
        }
    }

    private fun addColorAdapter() {
        val colorAdapter = ColorAdapter(colorModels)
        var recyclerView = findViewById<RecyclerView>(R.id.colors_recycler_view)
        recyclerView.adapter = colorAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun addLimbsAdapter() {
        val limbAdapter = LimbAdapter(limbModels)
        var recyclerView = findViewById<RecyclerView>(R.id.limbs_recycler_view)
        recyclerView.adapter = limbAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpLimbs() {
        val limbNames = resources.getStringArray(R.array.limb_names)
        for (limbName in limbNames) {
            limbModels.add(LimbModel(limbName))
        }
    }
}