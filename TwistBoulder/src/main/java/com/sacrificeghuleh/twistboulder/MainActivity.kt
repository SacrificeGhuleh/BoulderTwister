package com.sacrificeghuleh.twistboulder

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sacrificeghuleh.twistboulder.colors.ColorAdapter
import com.sacrificeghuleh.twistboulder.colors.ColorModel
import com.sacrificeghuleh.twistboulder.limbs.LimbAdapter
import com.sacrificeghuleh.twistboulder.limbs.LimbModel

class MainActivity : AppCompatActivity() {
    private var twistBoulderSetting =
        BoulderSettings(ArrayList(), ArrayList())

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
        val colNamesAdj =
            resources.getStringArray(R.array.boulder_colors_names_adjectives) //Array<String>
        val colCodes = resources.getIntArray(R.array.boulder_colors) // IntArray
        val adjColCodes = resources.getIntArray(R.array.boulder_bg_colors) // IntArray

        for (i in colNames.indices) {
            Log.d("MainActivity", "Setting up color $i")
            twistBoulderSetting.colorModels.add(
                ColorModel(
                    colNames[i],
                    colNamesAdj[i],
                    Color.valueOf(colCodes[i]),
                    Color.valueOf(adjColCodes[i]),
                    R.drawable.circle
                )
            )
        }

        twistBoulderSetting.colorModels[twistBoulderSetting.colorModels.size - 1].svgResourceVal =
            R.drawable.circlecolorful
    }

    private fun setUpLimbs() {
        val limbNames = resources.getStringArray(R.array.limb_names)
        for (limbName in limbNames) {
            Log.d("MainActivity", "Setting up limb $limbName")
            twistBoulderSetting.limbModels.add(LimbModel(limbName, true))
        }
    }

    private fun addColorAdapter() {
        val colorAdapter = ColorAdapter(twistBoulderSetting.colorModels)
        val recyclerView = findViewById<RecyclerView>(R.id.colors_recycler_view)
        recyclerView.adapter = colorAdapter
        // recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun addLimbsAdapter() {
        val limbAdapter = LimbAdapter(twistBoulderSetting.limbModels)
        val recyclerView = findViewById<RecyclerView>(R.id.limbs_recycler_view)
        recyclerView.adapter = limbAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun startTwist(@Suppress("UNUSED_PARAMETER") view: View) {
        val intent = Intent(this, TwistActivity::class.java)

        var parcel = BoulderSettings(ArrayList(), ArrayList())

        twistBoulderSetting.apply {
            for (colorModel in colorModels) {
                if (colorModel.enabled) {
                    parcel.colorModels.add(colorModel)
                }
            }

            for (limbModel in limbModels) {
                if (limbModel.enabled) {
                    parcel.limbModels.add(limbModel)
                }
            }
        }

        intent.putExtra(BoulderParcelKey, parcel)
        startActivity(intent)
    }
}