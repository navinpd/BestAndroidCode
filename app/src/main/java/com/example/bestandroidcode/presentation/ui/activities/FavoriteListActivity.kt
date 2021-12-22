package com.example.bestandroidcode.presentation.ui.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestandroidcode.R
import com.example.bestandroidcode.presentation.ui.adapter.FavoriteCatAdapter
import com.example.bestandroidcode.util.favList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_favorite_list.*
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteListActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPref: SharedPreferences

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_list)

        title = getString(R.string.fav_cat_String)

        val currentFavoriteList = sharedPref.getStringSet(favList, HashSet())

        viewManager = LinearLayoutManager(this)
        viewAdapter = FavoriteCatAdapter(currentFavoriteList!!.toTypedArray())

        if (currentFavoriteList.isNotEmpty())
            noCatMessage.visibility = View.GONE

        rvFavorite.layoutManager = viewManager
        rvFavorite.adapter = viewAdapter
    }
}