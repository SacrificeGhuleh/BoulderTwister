package com.sacrificeghuleh.twistboulder

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sacrificeghuleh.twistboulder.colors.ColorAdapter
import com.sacrificeghuleh.twistboulder.colors.ColorModel
import com.sacrificeghuleh.twistboulder.limbs.LimbAdapter
import com.sacrificeghuleh.twistboulder.limbs.LimbModel
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var twistBoulderSetting =
        TwistBoulderSetting(ArrayList(), ArrayList())

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
            Log.d("MainActivity", "Setting up color $colName")
            twistBoulderSetting.colorModels.add(
                ColorModel(
                    colName,
                    Color.valueOf(colCode),
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
        var recyclerView = findViewById<RecyclerView>(R.id.colors_recycler_view)
        recyclerView.adapter = colorAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun addLimbsAdapter() {
        val limbAdapter = LimbAdapter(twistBoulderSetting.limbModels)
        var recyclerView = findViewById<RecyclerView>(R.id.limbs_recycler_view)
        recyclerView.adapter = limbAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun startTwist(view: View) {
        val intent = Intent(this, TwistActivity::class.java)

        var testParcel = TestParcelClass(ArrayList(), ArrayList())

        twistBoulderSetting.apply {
            for (colorModel in colorModels) {
                if (colorModel.enabled) {
                    testParcel.colorModels.add(colorModel)
                }
            }

            for (limbModel in limbModels) {
                if (limbModel.enabled) {
                    testParcel.limbModels.add(limbModel)
                }
            }
        }

        intent.putExtra("Test", testParcel)
        startActivity(intent)
    }
}