package com.manuelcarvalho.nasaapi.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.manuelcarvalho.nasaapi.R
import com.manuelcarvalho.nasaapi.viewmodel.NasaViewModel

private const val TAG = "FirstFragment"
class FirstFragment : Fragment() {

    private lateinit var viewModel: NasaViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[NasaViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        observeViewModel()

    }

    private fun observeViewModel() {


        viewModel.photoList.observe(viewLifecycleOwner, Observer { listURL ->
            listURL?.let {
                Log.d(TAG, "${it}")
            }
        })


    }


}