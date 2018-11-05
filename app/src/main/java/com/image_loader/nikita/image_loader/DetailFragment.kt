package com.image_loader.nikita.image_loader

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.image_loader.nikita.image_loader.utils.AsyncLoadFull
import com.image_loader.nikita.image_loader.utils.AsyncLoadPreviewImage


class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.image_detail_view, container, false)
        val imageHolder = view.findViewById<ImageView>(R.id.detailsImage)
        val bundle = this.arguments

        val url = bundle?.getString("full_url")
        if(url != null) {
            AsyncLoadFull(imageHolder).execute(url)
        }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}