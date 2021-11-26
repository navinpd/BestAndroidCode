package com.example.bestandroidcode.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.RequestManager
import com.example.bestandroidcode.R
import com.example.bestandroidcode.data.remote.model.Cat
import com.example.bestandroidcode.databinding.MainFragmentBinding
import com.example.bestandroidcode.presentation.ui.activities.MainActivity
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel
import com.example.bestandroidcode.util.obtainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)

        viewModel.randomCatLiveData.observe(viewLifecycleOwner, { value ->
            when(value) {
                is MainViewModel.CurrentViewState.ShowLoading -> {
                    //TODO hide progress bar
                }
                is MainViewModel.CurrentViewState.HideLoading -> {
                    //TODO hide progress bar
                }
                is MainViewModel.CurrentViewState.ShowError -> {
                    val activity = activity as MainActivity
                    activity.refreshFavoriteButton(currentCatObject!!.url)

                    Toast.makeText(context, value.message, Toast.LENGTH_LONG).show()
                }
                is MainViewModel.CurrentViewState.ShowData -> {
                    val activity = activity as MainActivity
                    activity.refreshFavoriteButton(currentCatObject!!.url)
                    glide.load(value.show?.url)
                        .into(ivCat)
                }
            }

            val activity = activity as MainActivity
            activity.refreshFavoriteButton(currentCatObject!!.url)
        })

        binding.btnLoadCat.setOnClickListener {
            viewModel.getRandomCat()
        }


        btnProUser.setOnClickListener {
            val advanceFragment = AdvanceFragment()

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, advanceFragment)
            transaction.addToBackStack(AdvanceFragment::class.java.simpleName)
            transaction.commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.main_fragment, container, false)
}