package com.image_loader.nikita.image_loader.utils

import android.graphics.Bitmap
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode

data class ShortImageModel(
    var authorName: String,
    var description: String,
    var fullLink: String,
    var previewLink: String,
    var fullImage: Bitmap?,
    var previewImage: Bitmap?
)
