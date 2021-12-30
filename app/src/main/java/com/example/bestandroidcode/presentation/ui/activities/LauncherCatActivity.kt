package com.example.bestandroidcode.presentation.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.bestandroidcode.R
import com.example.bestandroidcode.databinding.MainActivityBinding
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LauncherCatActivity : AppCompatActivity() {
    private var mOptionsMenu: Menu? = null
    private lateinit var binding: MainActivityBinding

    private val viewModel by viewModels<MainViewModel> {
        defaultViewModelProviderFactory
    }

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.liveData.observe(this, {
            refreshFavoriteButton(false)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        mOptionsMenu = menu

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_favorite -> {
            val isAdded = viewModel.updateFavCatList()
            refreshFavoriteButton(isAdded)
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

    fun refreshFavoriteButton(isAdded: Boolean) {
        mOptionsMenu?.findItem(R.id.action_favorite)?.setIcon(
            if (isAdded) {
                R.drawable.baseline_favorite_black_24
            } else {
                R.drawable.baseline_favorite_border_black_24
            }
        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!isFinishing) {
            mOptionsMenu?.findItem(R.id.action_favorite)
                ?.setIcon(R.drawable.baseline_favorite_border_black_24)
        }
    }
}