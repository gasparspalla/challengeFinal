package com.munidigital.bc2201.challengefinal.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.munidigital.bc2201.challengefinal.IAlert

import com.munidigital.bc2201.challengefinal.R
import com.munidigital.bc2201.challengefinal.activities.LoginActivity

class SesionFragment : Fragment() {
    private lateinit var viewModel:ViewModelLogin
    private lateinit var button_close_session:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView=inflater.inflate(R.layout.fragment_session, container, false)

        viewModel= ViewModelProvider(this).get(ViewModelLogin::class.java)

        button_close_session=rootView.findViewById(R.id.btnCloseSession)
        button_close_session.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
        return rootView
    }


}