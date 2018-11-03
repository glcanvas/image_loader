package com.image_loader.nikita.image_loader.utils

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.image_loader.nikita.image_loader.R


open class ImageListViewAdapter(
    private val items: ArrayList<ShortImageModel>
) : RecyclerView.Adapter<ImageListViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ImageListViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.image_list_item, p0, false)
        return ImageListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: ImageListViewHolder, p1: Int) {
        val img = items[p1]
        p0.author_name.text = img.authorName
        p0.description.text = img.description
    }


}
