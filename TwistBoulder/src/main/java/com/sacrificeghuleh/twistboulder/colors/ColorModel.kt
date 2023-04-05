package com.sacrificeghuleh.twistboulder.colors

import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable

data class ColorModel(
    val name: String,
    val color: android.graphics.Color,
    var svgResourceVal: Int,
    var enabled: Boolean = true
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() as String,
        Color.valueOf(parcel.readInt()),
        parcel.readInt(),
        parcel.readInt() == 1
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(color.toArgb())
        parcel.writeInt(svgResourceVal)
        parcel.writeInt(if (enabled) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ColorModel> {
        override fun createFromParcel(parcel: Parcel): ColorModel {
            return ColorModel(parcel)
        }

        override fun newArray(size: Int): Array<ColorModel?> {
            return arrayOfNulls(size)
        }
    }
}
