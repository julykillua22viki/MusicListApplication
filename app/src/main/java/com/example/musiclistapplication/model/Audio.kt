package com.example.musiclistapplication.model

import android.os.Parcel
import android.os.Parcelable

data class Audio(
    val element: String?,
    val type: String?,
    val text: String?,
    val URL: String?,
    val bitrate: String?,
    val reliability: String?,
    val guide_id: String?,
    val subtext: String?,
    val genre_id: String?,
    val formats: String?,
    val playing: String?,
    val item: String?,
    val image: String?,
    val now_playing_id: String?,
    val preset_id: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(element)
        parcel.writeString(type)
        parcel.writeString(text)
        parcel.writeString(URL)
        parcel.writeString(bitrate)
        parcel.writeString(reliability)
        parcel.writeString(guide_id)
        parcel.writeString(subtext)
        parcel.writeString(genre_id)
        parcel.writeString(formats)
        parcel.writeString(playing)
        parcel.writeString(item)
        parcel.writeString(image)
        parcel.writeString(now_playing_id)
        parcel.writeString(preset_id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Audio> {
        override fun createFromParcel(parcel: Parcel): Audio {
            return Audio(parcel)
        }

        override fun newArray(size: Int): Array<Audio?> {
            return arrayOfNulls(size)
        }
    }
}