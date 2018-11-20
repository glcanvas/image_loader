package com.imageLoader.nikita.imageLoader.services

import android.content.Intent
import android.util.Log
import com.imageLoader.nikita.imageLoader.utils.CommonData
import java.net.URL

class BackgroundPreviewImageLoad : CommonBackgroundLoad() {
    override fun onHandleIntent(intent: Intent?) {
        val url = intent?.getStringExtra("preview_url")
        val offset = intent?.getIntExtra("array_offset", 0)
        val imageUrl = URL(url)
        val broadcastIntent = Intent()
        broadcastIntent.action = CommonData.PROCESS_RESPONSE_PREVIEW + "_$offset"
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT)
        if (url == null) {
            broadcastIntent.putExtra(CommonData.PARAM_STATUS, "fail")
            sendBroadcast(broadcastIntent)
            Log.e("image_loader", "url is null")
            return
        }
        val regex = Regex("[^a-zA-Z0-9]")
        val correctWay = regex.replace(url, "")
        if (fileExist(correctWay)) {
            Log.d("image_loader", "load from data storage")
            loadFileFromStorage(correctWay, broadcastIntent)
        } else {
            Log.d("image_loader", "load from internet")
            loadFileFromInternet(imageUrl, correctWay, broadcastIntent)
        }


    }
}