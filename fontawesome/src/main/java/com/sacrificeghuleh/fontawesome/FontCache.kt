package com.sacrificeghuleh.fontawesome

import android.content.Context
import android.graphics.Typeface
import com.sacrificeghuleh.fontawesome.FontCache
import java.lang.Exception
import java.util.*

object FontCache {
    const val FA_FONT_REGULAR = "fa-regular-400.ttf"
    const val FA_FONT_SOLID = "fa-solid-900.ttf"
    const val FA_FONT_BRANDS = "fa-brands-400.ttf"
    private val fontCache = Hashtable<String, Typeface?>()

    @JvmStatic
    operator fun get(context: Context, name: String): Typeface? {
        var typeface = fontCache[name]
        if (typeface == null) {
            typeface = try {
                Typeface.createFromAsset(context.assets, name)
            } catch (e: Exception) {
                return null
            }
            fontCache[name] = typeface
        }
        return typeface
    }
}