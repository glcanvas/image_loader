package com.imageLoader.nikita.imageLoader.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.imageLoader.nikita.imageLoader.services.BackgroundPreviewImageLoad
import java.lang.ref.WeakReference

class CommonData {
    companion object {
        const val PROCESS_RESPONSE_DETAIL = "com.image_loader.nikita.image_loader.PROCESS_RESPONSE"
        const val PROCESS_RESPONSE_PREVIEW = "com.image_loader.nikita.image_loader.PROCESS_RESPONSE_PREVIEW"
        const val PARAM_STATUS = "status"
        const val PARAM_RESULT = "result"
        fun handlerLoadPreview(
            adapter: ImageListViewAdapter, element: ShortImageModel, offset: Int,
            context: WeakReference<Context?>, imageBackgroundSet: HashSet<BroadcastReceiver>
        ) {
            val broadcastReceiver = PreviewHandler(adapter, element, offset).detailReceiver
            val filter = IntentFilter(PROCESS_RESPONSE_PREVIEW + "_$offset")

            filter.addCategory(Intent.CATEGORY_DEFAULT)

            context.get()?.registerReceiver(broadcastReceiver, filter)

            val intent = Intent(context.get(), BackgroundPreviewImageLoad::class.java)
            intent.putExtra("preview_url", element.previewLink)
            intent.putExtra("array_offset", offset)
            context.get()?.startService(intent)
            imageBackgroundSet.add(broadcastReceiver)

        }
    }
}