package com.example.bestandroidcode.presentation.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.bestandroidcode.R
import com.example.bestandroidcode.databinding.MainActivityBinding
import com.example.bestandroidcode.presentation.ui.fragment.AdvanceCatFragment
import com.example.bestandroidcode.presentation.ui.fragment.RandomCatFragment
import com.example.bestandroidcode.util.favList
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LauncherCatActivity : AppCompatActivity() {
    private var mOptionsMenu: Menu? = null
    private lateinit var binding: MainActivityBinding

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        mOptionsMenu = menu

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_favorite -> {

            val currentFavoriteList = HashSet<String>(sharedPref.getStringSet(favList, HashSet<String>()))

            val hostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
            val fragment = hostFragment?.childFragmentManager?.fragments?.get(0)
            var catImageUrl = ""

            if (fragment is RandomCatFragment && fragment.currentCatObject != null) {
                catImageUrl = fragment.currentCatObject!!.url

            } else if (fragment is AdvanceCatFragment && fragment.currentCatObject != null) {
                catImageUrl = fragment.currentCatObject!!.url

            }

            if (catImageUrl.isNotEmpty() && catImageUrl.isNotBlank()) {
                if (currentFavoriteList.contains(catImageUrl)) {
                    currentFavoriteList.remove(catImageUrl)
                } else {
                    currentFavoriteList.add(catImageUrl)
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