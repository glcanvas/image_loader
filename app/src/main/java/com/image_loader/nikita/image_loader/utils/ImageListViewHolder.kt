package com.image_loader.nikita.image_loader.utils

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.image_loader.nikita.image_loader.R

class ImageListViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var author_name = view.findViewById<TextView>(R.id.holder_author_name)
    var description = view.findViewById<TextView>(R.id.holder_description)

}