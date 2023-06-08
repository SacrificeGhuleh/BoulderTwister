package com.sacrificeghuleh.twistboulder

import android.graphics.PorterDuff
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.sacrificeghuleh.twistboulder.colors.ColorModel
import com.sacrificeghuleh.twistboulder.limbs.LimbModel
import kotlin.math.max
import kotlin.random.Random

class TwistActivity : AppCompatActivity() {
    private var step = 0
    private var best = 0

    private var limbModels: ArrayList<LimbModel> = ArrayList()
    private var colorModels: ArrayList<ColorModel>? = null

    private var currentLimb: LimbModel? = null
    private var currentColor: ColorModel? = null

    private var lastLimb: LimbModel? = null

    private val numberOfRetries = 3


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twist)

        try {
            val models = intent.extras?.getParcelable(
                BoulderParcelKey, BoulderSettings::class.java
            )!!
            Log.w(BoulderParcelKey, models.toString())

            colorModels = models.colorModels
            limbModels = models.limbModels


        } catch (e: Exception) {
            Log.e("TwistActivity", e.toString())
        }

        generate()
    }


    fun generate() {
        step++
        lastLimb = currentLimb
        var generatedLimb: LimbModel? = null
        for (i in 0..numberOfRetries) {
            generatedLimb = limbModels[Random.nextInt(limbModels.size)]
            if (generatedLimb != lastLimb) break
        }
        currentLimb = generatedLimb
        currentColor = colorModels!![Random.nextInt(colorModels!!.size)]

        onRegenerate()
    }

    private fun onRegenerate() {
        var imageView = findViewById<ImageView>(R.id.imageView)
        var imageViewBg = findViewById<ImageView>(R.id.imageViewBG)

        imageView.clearColorFilter()
        imageViewBg.clearColorFilter()

        imageView.setImageResource(currentColor!!.svgResourceVal)

        if (currentColor!!.svgResourceVal != R.drawable.circlecolorful) {
            imageViewBg.setImageResource(currentColor!!.svgResourceVal)

            imageView.setColorFilter(currentColor!!.color.toArgb(), PorterDuff.Mode.SRC_IN)
            imageViewBg.setColorFilter(
                currentColor!!.colorAdjacent.toArgb(),
                PorterDuff.Mode.SRC_IN
            )
        } else {
            imageViewBg.setImageResource(R.drawable.circlecolorful_bg)
        }

        var stepTextView = findViewById<TextView>(R.id.stepTextView)
        stepTextView.text = resources.getString(R.string.step_string, step)

        var descriptTextView = findViewById<TextView>(R.id.descriptTextView)
        descriptTextView.text = resources.getString(
            R.string.placement_string,
            currentLimb!!.name,
            currentColor!!.nameAdjective
        )
    }

    fun onClickGenerate(@Suppress("UNUSED_PARAMETER") view: View) {
        generate()
    }

    fun onClickReset(@Suppress("UNUSED_PARAMETER") view: View) {
        best = max(best, step)
        step = 0
        generate()

    }

}
