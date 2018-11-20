package com.imageLoader.nikita.imageLoader.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar

class DetailHandler(val progressBar: ProgressBar, val imageHolder: ImageView) {
    private var receiver: BroadcastReceiver
    val detailReceiver: BroadcastReceiver
        get() = this.receiver

    init {
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val status = intent?.getStringExtra(CommonData.PARAM_STATUS)
                if (status == "ok") {
                    val image = intent.getParcelableExtra<Bitmap>(CommonData.PARAM_RESULT)
                    progressBar.visibility = View.GONE
                    imageHolder.setImageBitmap(image)
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        }
    }


}