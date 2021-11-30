package com.example.bestandroidcode.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.bestandroidcode.R
import com.example.bestandroidcode.data.remote.model.Cat
import com.example.bestandroidcode.databinding.MainFragmentBinding
import com.example.bestandroidcode.presentation.ui.activities.LauncherCatActivity
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.cat_view.view.*
import javax.inject.Inject

@AndroidEntryPoint
class RandomCatFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> {
        defaultViewModelProviderFactory
    }

    @Inject
    lateinit var glide: RequestManager

    var currentCatObject: Cat? = null
    private var binding: MainFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mergedCatIV = binding?.container?.ivCat

//        lifecycleScope.launchWhenStarted {
//            val drama = viewModel.getRandomCat()
//            Toast.makeText(activity, drama.cat?.id, Toast.LENGTH_LONG).show()
//
//            glide.load(drama.cat?.url).into(mergedCatIV?.ivCat!!)
//        }

        binding?.btnLoadCat?.setOnClickListener {
            viewModel.getRandomCat().observe(viewLifecycleOwner, { value ->
                Log.d(javaClass.simpleName, "Fragment $value")
                when (value) {
                    is MainViewModel.CurrentViewState.ShowLoading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is MainViewModel.CurrentViewState.HideLoading -> {
                        binding?.progressBar?.visibility = View.GONE
                    }
                    is MainViewModel.CurrentViewState.ShowError -> {
                        Toast.makeText(context, value.message, Toast.LENGTH_LONG).show()
                    }
                    is MainViewModel.CurrentViewState.ShowData -> {
                        val activity = activity as LauncherCatActivity
                        currentCatObject = value.item
                        activity.refreshFavoriteButton(currentCatObject!!.url)
                        glide.load(value.item?.url)
                            .into(mergedCatIV?.ivCat!!)
                    }
                }

            })
        }

        binding?.btnProUser?.setOnClickListener {
            if (requireActivity() is LauncherCatActivity) {
                findNavController()
                    .navigate(R.id.randomCatFragment_to_advanceCatFragment)
            }
        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}