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

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twist)
        var textView = findViewById<TextView>(R.id.descriptTextView)

        try {
            val settings: TwistBoulderSetting =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    intent.extras?.getParcelable(
                        TwistBoulderSettingKey,
                        TwistBoulderSetting::class.java
                    )!!
                else
                    intent.extras?.getParcelable<TwistBoulderSetting>(TwistBoulderSettingKey) as TwistBoulderSetting

            textView.text = settings.toString()
        } catch (e: Exception) {
            Log.e("TwistActivity", e.toString())
        }

        try {
            val test = intent.extras?.getParcelable(
                "Test", TestParcelClass::class.java
            )!!
            Log.w("TwistActivity TEST", test.toString())
            textView.text = test.toString()

            colorModels = test.colorModels
            limbModels = test.limbModels


        } catch (e: Exception) {
            Log.e("TwistActivity", e.toString())
        }

        generate()
    }


    fun generate() {
        step++
        currentLimb = limbModels[Random.nextInt(limbModels.size)]
        currentColor = colorModels!![Random.nextInt(colorModels!!.size)]

        onRegenerate()
    }

    private fun onRegenerate() {
        var imageView = findViewById<ImageView>(R.id.imageView)
        imageView.clearColorFilter()
        imageView.setImageResource(currentColor!!.svgResourceVal)
        if (currentColor!!.svgResourceVal != R.drawable.circlecolorful)
            imageView.setColorFilter(currentColor!!.color.toArgb(), PorterDuff.Mode.SRC_IN)

        var stepTextView = findViewById<TextView>(R.id.stepTextView)
        stepTextView.text = resources.getString(R.string.step_string, step)

        var descriptTextView = findViewById<TextView>(R.id.descriptTextView)
        descriptTextView.text = resources.getString(
            R.string.placement_string,
            currentLimb!!.name,
            currentColor!!.nameAdjective
        )
    }

    fun onClickGenerate(view: View) {
        generate()
    }

    fun onClickReset(view: View) {
        best = max(best, step)
        step = 0
        generate()

    }

}
