package com.image_loader.nikita.image_loader.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import java.io.IOException
import java.net.URL

class AsyncLoadFull(private val imageView: ImageView) : AsyncTask<String, Unit, Bitmap>() {
    override fun doInBackground(vararg params: String?): Bitmap {
        val stringUrl = params[0]
        val imageUrl = URL(stringUrl)
        if (isCancelled) {
            return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        }
        try {
            return BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream())
            //bitmap.compress(Bitmap.CompressFormat.JPEG, 50, bitmap.)
        } catch (e: IOException) {
            Log.e("image_loader", e.toString())
        } catch (e: IllegalArgumentException) {
            Log.e("image_loader", e.toString())
        }
        return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    }

    override fun onPostExecute(result: Bitmap?) {
        imageView.setImageBitmap(result)
    }
}