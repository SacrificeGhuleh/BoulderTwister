package com.sacrificeghuleh.twistboulder

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var colorModels = ArrayList<ColorModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpColors()

        val colorAdapter = ColorAdapter(colorModels)

        var recyclerView = findViewById<RecyclerView>(R.id.colors_recycler_view)
        recyclerView.adapter = colorAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun setUpColors() {
        val colNames = resources.getStringArray(R.array.colors_array) //Array<String>
        val colCodes = resources.getIntArray(R.array.boulder_colors) // IntArray

        colCodes.zip(colNames) { colCode, colName ->
            colorModels.add(ColorModel(colName, Color.valueOf(colCode)))
        }
    }
}