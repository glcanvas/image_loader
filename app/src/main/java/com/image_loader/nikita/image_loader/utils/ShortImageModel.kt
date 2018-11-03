package com.image_loader.nikita.image_loader.utils

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode

data class ShortImageModel(
    var authorName: String,
    var description: String,
    var fullLink: String,
    var previewLink: String
) {
    init {
    }



    fun fromJson(node: ObjectNode) {
        this.description = (node.get("description") as JsonNode?)?.textValue() ?: ""
        this.authorName = (node.get("user") as JsonNode?)?.get("name")?.textValue() ?: ""
        val urls = (node.get("urls") as JsonNode?)
        this.fullLink = urls?.get("full")?.asText() ?: ""
        this.previewLink = urls?.get("thumb")?.asText() ?: ""
    }
}
