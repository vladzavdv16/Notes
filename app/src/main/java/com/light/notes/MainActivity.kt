package com.light.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.light.notes.utils.APP_ACTIVITY
import com.light.notes.utils.AppPreference
import com.light.notes.zavodov.R
import com.light.notes.zavodov.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        APP_ACTIVITY = this
        setSupportActionBar(binding.toolbar)

        navController =
            Navigation.findNavController(APP_ACTIVITY, R.id.nav_host_fragment)
        title = getString(R.string.main_fragment_title)

        AppPreference.getPreference(this)

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}