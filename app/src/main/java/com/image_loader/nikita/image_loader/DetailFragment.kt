package com.image_loader.nikita.image_loader

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.image_detail_view, container, false)
        val bundle = this.arguments
        //val t = detailsText
        val pos = bundle?.getLong("position")
        val detailsText = view.findViewById<TextView>(R.id.detailsText)
        detailsText.text = pos.toString()
        Log.d("test", "cv")
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("text", "oc")
    }

}