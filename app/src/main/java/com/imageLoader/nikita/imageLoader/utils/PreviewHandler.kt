package com.imageLoader.nikita.imageLoader.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.util.Log

class PreviewHandler(val adapter: ImageListViewAdapter, val element: ShortImageModel, val offset: Int) {
    private var receiver: BroadcastReceiver
    val detailReceiver: BroadcastReceiver
        get() = this.receiver

    init {
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val status = intent?.getStringExtra(CommonData.PARAM_STATUS)
                if (status == "ok") {
                    val image = intent.getParcelableExtra<Bitmap>(CommonData.PARAM_RESULT)
                    element.previewImage = image
                    try {
                        adapter.notifyItemChanged(offset)
                    } catch (e: IndexOutOfBoundsException) {
                        Log.e("image_loader", e.toString())
                    }
                }
            }
        }
    }


}