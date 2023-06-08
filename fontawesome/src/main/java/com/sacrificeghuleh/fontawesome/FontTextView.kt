package com.sacrificeghuleh.fontawesome

import android.content.Context
import com.sacrificeghuleh.fontawesome.FontCache.get
import androidx.appcompat.widget.AppCompatTextView
import kotlin.jvm.JvmOverloads
import android.content.res.TypedArray
import android.util.AttributeSet
import com.sacrificeghuleh.fontawesome.R
import com.sacrificeghuleh.fontawesome.FontCache

class FontTextView : StrokedTextView {
    private var isBrandingIcon = false
    private var isSolidIcon = false

    constructor(context: Context?) : super(context!!) {}

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int = 0) : super(
        context,
        attrs,
        defStyle
    ) {
        val a = context.theme.obtainStyledAttributes(
            attrs, R.styleable.FontTextView,
            0, 0
        )
        isSolidIcon = a.getBoolean(R.styleable.FontTextView_solid_icon, false)
        isBrandingIcon = a.getBoolean(R.styleable.FontTextView_brand_icon, false)
        init()
    }

    private fun init() {
        typeface =
            if (isBrandingIcon) FontCache[context, "fa-brands-400.ttf"] else if (isSolidIcon) FontCache[context, "fa-solid-900.ttf"] else FontCache[context, "fa-regular-400.ttf"]
    }
}