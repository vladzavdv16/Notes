package com.light.notes.ui.add_new_notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.light.notes.model.AppNote
import com.light.notes.utils.APP_ACTIVITY
import com.light.notes.utils.showToast
import com.light.notes.zavodov.R
import com.light.notes.zavodov.databinding.FragmentAddNewBinding

class AddNewNotesFragment : Fragment() {

    private lateinit var binding: FragmentAddNewBinding
    private lateinit var viewModel: AddNewNotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddNewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        initialization()
    }

    private fun initialization() {
        viewModel = ViewModelProvider(this).get(AddNewNotesViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val text = binding.etSurname.text.toString()

            if (name.isEmpty()) {
                showToast(getString(R.string.toast_enter_name))
            } else {
                viewModel.insert(AppNote(name = name, text = text)){
                    APP_ACTIVITY.navController.navigate(R.id.action_addNewFragment_to_mainFragment)
                }
            }
        }
    }

}