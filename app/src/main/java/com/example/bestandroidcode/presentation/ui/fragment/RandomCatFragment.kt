package com.example.bestandroidcode.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.bestandroidcode.R
import com.example.bestandroidcode.data.remote.model.Cat
import com.example.bestandroidcode.databinding.MainFragmentBinding
import com.example.bestandroidcode.presentation.ui.activities.LauncherCatActivity
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel
import com.example.bestandroidcode.util.obtainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.cat_view.view.*
import javax.inject.Inject

@AndroidEntryPoint
class RandomCatFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        obtainViewModel(
            requireActivity(),
            MainViewModel::class.java,
            defaultViewModelProviderFactory
        )
    }

    @Inject
    lateinit var glide: RequestManager

    var currentCatObject: Cat? = null
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mergedCatIV = binding.container.ivCat

        viewModel.randomCatLiveData.observe(viewLifecycleOwner, { value ->
            Log.d(javaClass.simpleName, "Fragment $value")
            when(value) {
                is MainViewModel.CurrentViewState.ShowLoading -> {
                    //TODO hide progress bar
                }
                is MainViewModel.CurrentViewState.HideLoading -> {
                    //TODO hide progress bar
                }
                is MainViewModel.CurrentViewState.ShowError -> {
                    Toast.makeText(context, value.message, Toast.LENGTH_LONG).show()
                }
                is MainViewModel.CurrentViewState.ShowData -> {
                    val activity = activity as LauncherCatActivity
                    currentCatObject = value.item
                    activity.refreshFavoriteButton(currentCatObject!!.url)
                    glide.load(value.item?.url)
                        .into(mergedCatIV.ivCat)
                }
            }

        })

        binding.btnLoadCat.setOnClickListener {
            viewModel.getRandomCat()
        }

        binding.btnProUser.setOnClickListener {
            if(requireActivity() is LauncherCatActivity) {
                findNavController()
                    .navigate(R.id.randomCatFragment_to_advanceCatFragment)
            }
        }
    }

}