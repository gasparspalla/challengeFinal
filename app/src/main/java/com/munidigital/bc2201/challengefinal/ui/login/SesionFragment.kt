package com.munidigital.bc2201.challengefinal.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider

import com.munidigital.bc2201.challengefinal.R
import com.munidigital.bc2201.challengefinal.activities.LoginActivity
import com.munidigital.bc2201.challengefinal.databinding.FragmentSessionBinding

class SesionFragment : Fragment() {
    private lateinit var viewModel:ViewModelLogin
    private lateinit var binding:FragmentSessionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSessionBinding.inflate(inflater, container, false)

        val rootView = binding.root

        viewModel= ViewModelProvider(this).get(ViewModelLogin::class.java)



        binding.btnCloseSession.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
        return rootView
    }


}