package com.sacrificeghuleh.twistboulder

import android.os.Parcel
import android.os.Parcelable
import com.sacrificeghuleh.twistboulder.colors.ColorModel
import com.sacrificeghuleh.twistboulder.limbs.LimbModel

const val BoulderParcelKey = "BoulderParcel"

data class TestParcelClass(
    val colorModels: ArrayList<ColorModel>,
    val limbModels: ArrayList<LimbModel>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readArrayList(ColorModel::class.java.classLoader)!! as ArrayList<ColorModel>,
        parcel.readArrayList(LimbModel::class.java.classLoader)!! as ArrayList<LimbModel>
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(colorModels)
        parcel.writeList(limbModels)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TestParcelClass> {
        override fun createFromParcel(parcel: Parcel): TestParcelClass {
            return TestParcelClass(parcel)
        }

        override fun newArray(size: Int): Array<TestParcelClass?> {
            return arrayOfNulls(size)
        }
    }
}