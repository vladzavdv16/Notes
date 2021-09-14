package com.light.notes.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.light.notes.utils.APP_ACTIVITY
import com.light.notes.zavodov.databinding.FragmentStartBinding
import com.light.notes.utils.TYPE_ROOM
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

        initialization()
    }

    private fun initialization() {
        viewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)

        binding.btnRoom.setOnClickListener {
            viewModel.initDatabase(TYPE_ROOM){
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }
    }
}