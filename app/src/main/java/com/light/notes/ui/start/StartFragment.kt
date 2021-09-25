package com.light.notes.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.light.notes.utils.*
import com.light.notes.zavodov.databinding.FragmentStartBinding
import com.light.notes.zavodov.R


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    private lateinit var viewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        if (AppPreference.getInitUser()) {
            viewModel.initDatabase(AppPreference.getTypeDb()) {
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        } else {
            initialization()
        }
    }

    private fun initialization() {
        binding.btnRoom.setOnClickListener {
            viewModel.initDatabase(TYPE_ROOM) {
                AppPreference.setInitUser(true)
                AppPreference.setTypeDB(TYPE_ROOM)
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }
        binding.btnFirebase.setOnClickListener {
            binding.etEmail.visibility = View.VISIBLE
            binding.etPassword.visibility = View.VISIBLE
            binding.btnLogin.visibility = View.VISIBLE
            binding.btnLogin.setOnClickListener {
                EMAIL = binding.etEmail.text.toString()
                PASSWORD = binding.etPassword.text.toString()
                if (EMAIL.isNotEmpty() && PASSWORD.isNotEmpty()) {
                    viewModel.initDatabase(TYPE_FIREBASE) {
                        AppPreference.setInitUser(true)
                        AppPreference.setTypeDB(TYPE_FIREBASE)
                        APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
                    }
                } else showToast(getString(R.string.toast_btn_firebase))

            }
        }
    }
}