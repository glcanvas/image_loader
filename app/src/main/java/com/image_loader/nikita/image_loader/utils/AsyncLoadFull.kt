package com.image_loader.nikita.image_loader.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import java.io.IOException
import java.lang.ref.WeakReference
import java.net.URL

class AsyncLoadFull(
    private val imageView: WeakReference<ImageView>,
    private val progressBar: WeakReference<ProgressBar>
) :
    AsyncTask<String, Unit, Bitmap>() {
    override fun doInBackground(vararg params: String?): Bitmap {
        val stringUrl = params[0]
        val imageUrl = URL(stringUrl)
        if (isCancelled) {
            return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        }
        try {
            val inputStream = imageUrl.openConnection().getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            return Bitmap.createScaledBitmap(bitmap, 512, 512, false)
        } catch (e: IOException) {
            Log.e("image_loader", e.toString())
        } catch (e: IllegalArgumentException) {
            Log.e("image_loader", e.toString())
        }
        return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    }

    override fun onPostExecute(result: Bitmap?) {
        progressBar.get()?.visibility = View.GONE
        imageView.get()?.setImageBitmap(result)
    }
}