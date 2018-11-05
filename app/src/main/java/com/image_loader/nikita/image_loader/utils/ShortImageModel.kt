package com.image_loader.nikita.image_loader.utils

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode

data class ShortImageModel(
    var authorName: String,
    var description: String,
    var fullLink: String,
    var previewLink: String,
    var fullImage: Bitmap?,
    var previewImage: Bitmap?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        null,
        null
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(authorName)
        parcel.writeString(description)
        parcel.writeString(fullLink)
        parcel.writeString(previewLink)
        parcel.writeValue(null)
        parcel.writeValue(null)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShortImageModel> {
        override fun createFromParcel(parcel: Parcel): ShortImageModel {
            return ShortImageModel(parcel)
        }

        override fun newArray(size: Int): Array<ShortImageModel?> {
            return arrayOfNulls(size)
        }
    }

}
