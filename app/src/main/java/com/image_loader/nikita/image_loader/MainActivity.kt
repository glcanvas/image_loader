package com.image_loader.nikita.image_loader

import android.content.Context
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.util.Log


class MainActivity : AppCompatActivity() {

    lateinit var fragmentManager: FragmentManager


    fun getDimension(context: Context): String {
        val screenLayout = context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK
        return when (screenLayout) {
            Configuration.SCREENLAYOUT_SIZE_SMALL -> "normal"
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> "normal"
            Configuration.SCREENLAYOUT_SIZE_LARGE -> "large"
            Configuration.SCREENLAYOUT_SIZE_XLARGE -> "large"
            else -> "undefined"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("tested", "activity create")
        setContentView(R.layout.activity_main)
        fragmentManager = supportFragmentManager
        val screenSize = getDimension(baseContext)
        val bundle = Bundle()
        bundle.putString("screen_size", screenSize)
        when (screenSize) {
            "normal" -> {
                val listFragment = ImageListFragment()
                listFragment.arguments = bundle
                fragmentManager.beginTransaction().replace(R.id.listholder, listFragment).commit()
            }
            "large" -> {
                val listFragment = ImageListFragment()
                val detailFragment = DetailFragment()
                listFragment.arguments = bundle
                fragmentManager.beginTransaction().replace(R.id.listholder, listFragment).commit()
                fragmentManager.beginTransaction().replace(R.id.detailholder, detailFragment).commit()
            }
            else -> {
                throw Error()
            }
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("tested", "activity start")
    }

    override fun onResume() {
        super.onResume()
        Log.d("tested", "activity resume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("tested", "activity pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("tested", "activity stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("tested", "activity destroy")
    }
}
