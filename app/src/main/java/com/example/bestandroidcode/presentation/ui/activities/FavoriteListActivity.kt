package com.example.bestandroidcode.presentation.ui.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestandroidcode.R
import com.example.bestandroidcode.presentation.ui.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_favorite_list.*

@AndroidEntryPoint
class FavoriteListActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val favList = "FAVORITE_LIST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_list)

        title = getString(R.string.fav_cat_String)

        val sharedPref = getSharedPreferences("default", Context.MODE_PRIVATE)
        val currentFavoriteList = sharedPref.getStringSet(favList, HashSet())

        viewManager = LinearLayoutManager(this)
        viewAdapter = FavoriteAdapter(currentFavoriteList!!.toTypedArray())

        rvFavorite.layoutManager = viewManager
        rvFavorite.adapter = viewAdapter
    }
}