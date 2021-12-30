package com.example.bestandroidcode.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.bestandroidcode.R
import com.example.bestandroidcode.databinding.MainFragmentBinding
import com.example.bestandroidcode.presentation.ui.activities.LauncherCatActivity
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.cat_view.view.*
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class RandomCatFragment : Fragment() {

//    private val viewModel by viewModels<MainViewModel> {
//        defaultViewModelProviderFactory
//    }

    private val viewModel: MainViewModel by activityViewModels()

    @Inject
    lateinit var glide: RequestManager

    private var binding: MainFragmentBinding? = null
    private var mergedCatIV: AppCompatImageView? = null

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
        mergedCatIV = binding?.container?.ivCat

//        lifecycleScope.launchWhenStarted {
//            val random = viewModel.getRandomCat()
//            Toast.makeText(activity, random.cat?.id, Toast.LENGTH_LONG).show()
//
//            glide.load(random.cat?.url).into(mergedCatIV?.ivCat!!)
//        }

        binding?.btnLoadCat?.setOnClickListener {
            viewModel.getRandomCat().observe(viewLifecycleOwner, { value ->
                Log.d(javaClass.simpleName, "Fragment $value")

                when (value) {
                    is MainViewModel.CurrentViewState.ShowLoading -> {
                        showLoading(true)
                    }

                    is MainViewModel.CurrentViewState.HideLoading -> {
                        showLoading(false)
                    }

                    is MainViewModel.CurrentViewState.ShowError -> {
                        Toast.makeText(context, value.message, Toast.LENGTH_LONG).show()
                    }

                    is MainViewModel.CurrentViewState.ShowData -> {
                        viewModel.unselectLikeButton()

                        glide.load(value.item?.url)
                            .into(mergedCatIV?.ivCat!!)
                        noCatSelected.visibility = View.GONE
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

    private fun showLoading(isLoading : Boolean) {
        mergedCatIV?.ivCat?.visibility =  if(isLoading) View.GONE else View.VISIBLE
        binding?.progressBar?.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}