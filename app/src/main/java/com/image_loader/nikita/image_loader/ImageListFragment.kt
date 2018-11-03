package com.image_loader.nikita.image_loader


import android.os.Bundle
import android.support.v4.app.Fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.image_loader.nikita.image_loader.utils.AsyncLoadList
import com.image_loader.nikita.image_loader.utils.ImageListViewAdapter
import com.image_loader.nikita.image_loader.utils.ShortImageModel
import java.util.*
import kotlin.collections.HashSet

class ImageListFragment : Fragment() {
    private val metaImages = ArrayList<ShortImageModel>()
    private val taskSet = HashSet<AsyncLoadList>()
    private var screenSize: String? = null
    private var offset: Int = 0
    private lateinit var adapter: ImageListViewAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("tested", "list fragment createView")

        val bundle = this.arguments
        val view = inflater.inflate(R.layout.image_list_view, container, false)

        screenSize = bundle?.getString("screen_size") ?: screenSize

        val recyclerView = view.findViewById<RecyclerView>(R.id.image_list)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        adapter = ImageListViewAdapter(metaImages)
        recyclerView.adapter = adapter

        val button = view.findViewById(R.id.switch_frame) as Button
        button.setOnClickListener {
            val async = AsyncLoadList(offset, adapter, metaImages, taskSet)
            async.execute("https://api.unsplash.com/photos/?client_id=73e14423b06e6a0f7715e4ea90b0c9b8f3e94fa21d6281ed2b730da4cb79d016")
            offset
        }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("screen_size", screenSize)
    }
    /*
    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val bundle = Bundle()
        bundle.putString("screen_size", screenSize)
        bundle.putLong("position", id)
        if (screenSize == "normal") {
            val detailFragment = DetailFragment()
            detailFragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.listholder, detailFragment)?.addToBackStack(null)
                ?.commit()
        } else {

        }
    }*/
}