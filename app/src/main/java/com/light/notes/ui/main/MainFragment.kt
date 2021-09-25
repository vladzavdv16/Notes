package com.light.notes.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.light.notes.model.AppNote
import com.light.notes.utils.APP_ACTIVITY
import com.light.notes.utils.AppPreference
import com.light.notes.zavodov.R
import com.light.notes.zavodov.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var observerList: Observer<List<AppNote>>

    companion object {
        fun click(note: AppNote) {
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noteFragment, bundle)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adapter = MainAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setHasOptionsMenu(true)
        initialization()
    }

    private fun initialization() {
        binding.recycler.adapter = adapter
        observerList = Observer {
            val list = it.asReversed()
            adapter.setList(list)
        }
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        viewModel.allNotes.observe(this, observerList)
        binding.fab.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNewFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_notes_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_exit -> {
                viewModel.signOut()
                APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_startFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.allNotes.removeObserver(observerList)
        binding.recycler.adapter = null
    }

}