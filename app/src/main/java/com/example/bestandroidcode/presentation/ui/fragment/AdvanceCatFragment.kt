package com.example.bestandroidcode.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.RequestManager
import com.example.bestandroidcode.R
import com.example.bestandroidcode.data.remote.model.Cat
import com.example.bestandroidcode.databinding.AdvanceFragmentBinding
import com.example.bestandroidcode.presentation.ui.activities.LauncherCatActivity
import com.example.bestandroidcode.presentation.viewmodel.MainViewModel
import com.example.bestandroidcode.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AdvanceCatFragment : Fragment(R.layout.advance_fragment) {

    var currentCatObject: Cat? = null
    private var viewBinding: AdvanceFragmentBinding? = null
    private val categoryIdList = arrayOf(5, 15, 1, 14, 2, 4, 7)
    private var selectedCategoryId: Int = -1
    private var variableA: Int = 0
    private var variableB: Int = 0

    private val viewModel by viewModels<MainViewModel> {
        defaultViewModelProviderFactory
    }

    @Inject
    lateinit var glide: RequestManager

    private lateinit var categoryList: Array<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = AdvanceFragmentBinding.bind(view)
        generateQuestion()
        categoryList = resources.getStringArray(R.array.cat_category)
        val mergedCatIV = viewBinding?.container?.ivCat

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBinding?.spCategory?.adapter = adapter

        viewBinding?.spCategory?.onItemSelectedListener = object : OnItemSelectedListener {
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

        viewBinding?.btnAnswer?.setOnClickListener {
            val answer = viewBinding?.etAnswer?.text.toString().toIntOrNull()

            if (answer != null && variableA + variableB == answer) {
                hideKeyboard()

                viewModel.getCatByCategory(selectedCategoryId.toString())
                    .observe(viewLifecycleOwner, { value ->
                        Log.d(javaClass.simpleName, "Fragment $value")
                        when (value) {
                            is MainViewModel.CurrentViewState.ShowLoading -> {
                                viewBinding?.progressBar?.visibility = View.VISIBLE
                            }
                            is MainViewModel.CurrentViewState.HideLoading -> {
                                viewBinding?.progressBar?.visibility = View.GONE
                            }
                            is MainViewModel.CurrentViewState.ShowError -> {
                                Toast.makeText(context, value.message, Toast.LENGTH_LONG).show()
                            }
                            is MainViewModel.CurrentViewState.ShowData -> {
                                val activity = activity as LauncherCatActivity
                                currentCatObject = value.item
                                activity.refreshFavoriteButton(currentCatObject!!.url)
                                mergedCatIV?.let {
                                    glide.load(value.item?.url)
                                        .into(it)
                                }

                                generateQuestion()
                                viewBinding?.etAnswer?.setText("")
                            }
                        }
                    })
            } else {
                Toast.makeText(
                    activity,
                    getString(R.string.wrong_answer_message),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    private fun generateQuestion() {
        variableA = (0..10).random()
        variableB = (0..10).random()

        viewBinding?.tvQuestion?.text = getString(R.string.math_question, variableA, variableB)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }

}