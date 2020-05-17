package com.waichee.randommeal01.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.waichee.randommeal01.databinding.FragmentStartBinding

class StartFragment: Fragment() {

    private val viewModel: StartViewModel by lazy {
        ViewModelProviders.of(this).get(StartViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentStartBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        viewModel.navigateToMealDetail.observe(this, Observer {
            if (it == true) {
                this.findNavController().navigate(StartFragmentDirections.actionStartFragmentToDetailFragment())
                viewModel.displayMealDetailComplete()
            }
        })


        return binding.root
    }
}