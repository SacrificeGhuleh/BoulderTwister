package com.sacrificeghuleh.twistboulder.limbs

import android.os.Parcel
import android.os.Parcelable

data class LimbModel(
    val name: String,
    var enabled: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() as String,
        parcel.readInt() == 1
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(if (enabled) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LimbModel> {
        override fun createFromParcel(parcel: Parcel): LimbModel {
            return LimbModel(parcel)
        }

        override fun newArray(size: Int): Array<LimbModel?> {
            return arrayOfNulls(size)
        }
    }
}
