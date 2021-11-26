package com.example.bestandroidcode.presentation.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.bestandroidcode.R
import com.example.bestandroidcode.presentation.ui.fragment.AdvanceFragment
import com.example.bestandroidcode.presentation.ui.fragment.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var sharedPref: SharedPreferences

    private var mOptionsMenu: Menu? = null
    private val favList = "FAVORITE_LIST"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        sharedPref = getSharedPreferences("default", Context.MODE_PRIVATE)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        mOptionsMenu = menu

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_favorite -> {

            val currentFavoriteList = sharedPref.getStringSet(favList, HashSet())

            val f: Fragment? = supportFragmentManager.findFragmentById(R.id.container)
            var catImageUrl = ""
            if (f is MainFragment && f.currentCatObject != null) {
                catImageUrl = f.currentCatObject!!.url
            } else if (f is AdvanceFragment && f.currentCatObject != null) {
                catImageUrl = f.currentCatObject!!.url
            }
            if (catImageUrl.isNotEmpty() && catImageUrl.isNotBlank()) {
                if (currentFavoriteList!!.contains(catImageUrl)) {
                    currentFavoriteList!!.remove(catImageUrl)
                } else {
                    currentFavoriteList!!.add(catImageUrl)
                }

                val e: SharedPreferences.Editor = sharedPref.edit()
                e.remove(favList).apply()

                e.putStringSet(favList, currentFavoriteList).apply()

                refreshFavoriteButton(catImageUrl)
            }

            true
        }

        R.id.action_favorite_list -> {
            val intent = Intent(this, FavoriteListActivity::class.java)
            startActivity(intent)
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    fun refreshFavoriteButton(catImageUrl: String) {
        if (mOptionsMenu != null) {
            val currentFavoriteList = sharedPref.getStringSet(favList, HashSet())

            val favoriteItem = mOptionsMenu!!.findItem(R.id.action_favorite)

            if (currentFavoriteList!!.contains(catImageUrl)) {
                favoriteItem.setIcon(R.drawable.baseline_favorite_black_24)
            } else {
                favoriteItem.setIcon(R.drawable.baseline_favorite_border_black_24)
            }
        }
    }
}