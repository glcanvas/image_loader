package com.imageLoader.nikita.imageLoader.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.AsyncTask
import android.util.Log
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.imageLoader.nikita.imageLoader.services.BackgroundPreviewImageLoad
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.lang.ref.WeakReference
import java.net.URL

class AsyncLoadList(
    offset: Int,
    private val adapter: ImageListViewAdapter,
    private val list: ArrayList<ShortImageModel>,
    private val set: HashSet<AsyncLoadList>,
    private val imageBackgroundSet: HashSet<BroadcastReceiver>,
    private val context: WeakReference<Context?>
) :
    AsyncTask<String, Unit, ArrayList<ShortImageModel>>() {
    var tmpOffset: Int = 0

    init {
        tmpOffset = offset
    }

    override fun onPreExecute() {
        set.add(this)
    }

    override fun onPostExecute(result: ArrayList<ShortImageModel>?) {
        set.remove(this)
        val listOffset = list.size
        list.addAll(result ?: emptyList())
        adapter.notifyDataSetChanged()
        result?.forEachIndexed { index, element ->
            val offset = listOffset + index
            CommonData.handlerLoadPreview(adapter, element, offset, context, imageBackgroundSet)
        }
    }

    override fun onCancelled(result: ArrayList<ShortImageModel>?) {
        set.remove(this)
    }

    override fun doInBackground(vararg params: String?): ArrayList<ShortImageModel> {

        val items = ArrayList<ShortImageModel>()
        val request = params[0]
        val currentUrl = URL("$request;page=$tmpOffset")
        if (isCancelled) {
            return items
        }
        try {

            val connection = currentUrl.openConnection()
            val resultString = BufferedReader(InputStreamReader(connection.getInputStream())).useLines { lines ->
                val tmp = StringBuilder()
                lines.forEach {
                    tmp.append(it)
                }
                tmp.toString()
            }

            val mapper = ObjectMapper()
            val root = mapper.readTree(resultString)
            if (isCancelled) {
                return items
            }
            for (item in root) {
                if (isCancelled) {
                    return items
                }
                items.add(buildImageModel(item))
            }


        } catch (e: IOException) {
            Log.e("image_load", e.toString())
        } catch (e: JsonProcessingException) {
            Log.e("image_load", e.toString())
        }

        return items
    }

    private fun buildImageModel(node: JsonNode): ShortImageModel {

        val description = (node.get("description") as JsonNode?)?.textValue() ?: ""
        val authorName = (node.get("user") as JsonNode?)?.get("name")?.textValue() ?: ""
        val urls = (node.get("urls") as JsonNode?)
        val fullLink = urls?.get("full")?.asText() ?: ""
        val previewLink = urls?.get("thumb")?.asText() ?: ""

        return ShortImageModel(
            authorName = authorName,
            description = description,
            fullLink = fullLink,
            previewLink = previewLink,
            previewImage = null
        )

    }
}