package com.example.bestandroidcode.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.RequestManager
import com.example.bestandroidcode.R
import com.example.bestandroidcode.data.remote.model.Cat
import com.example.bestandroidcode.databinding.AdvanceFragmentBinding
import com.example.bestandroidcode.presentation.ui.activities.MainActivity
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel
import com.example.bestandroidcode.util.obtainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AdvanceFragment : Fragment(R.layout.advance_fragment) {

    var currentCatObject: Cat? = null
    private lateinit var viewBinding: AdvanceFragmentBinding
    private val categoryIdList = arrayOf(5, 15, 1, 14, 2, 4, 7)
    private var selectedCategoryId: Int = -1
    private var variableA: Int = 0
    private var variableB: Int = 0

    private val viewModel: MainViewModel by lazy {
        obtainViewModel(
            requireActivity(),
            MainViewModel::class.java,
            defaultViewModelProviderFactory
        )
    }

    @Inject
    lateinit var glide: RequestManager

    private val categoryList = arrayOf(R.array.cat_category)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = AdvanceFragmentBinding.bind(view)
        generateQuestion()

        viewModel.categoryCatLiveData.observe(viewLifecycleOwner, { value ->
            when (value) {
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
                        .into(viewBinding.ivCat)

                    generateQuestion()
                    viewBinding.etAnswer.setText("")
                }
            }
        })

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBinding.spCategory.adapter = adapter

        viewBinding.spCategory.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedCategoryId = categoryIdList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        viewBinding.btnAnswer.setOnClickListener {
            val answer = viewBinding.etAnswer.text.toString().toIntOrNull()

            if (answer != null && variableA + variableB == answer) {

                viewModel.getCatByCategory(selectedCategoryId.toString())
            } else {
                Toast.makeText(
                    activity,
                    getString(R.string.wrong_answer_message),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.advance_fragment, container, false)

    private fun generateQuestion() {
        variableA = (0..10).random()
        variableB = (0..10).random()

        viewBinding.tvQuestion.text = getString(R.string.math_question, variableA, variableB)
    }

}