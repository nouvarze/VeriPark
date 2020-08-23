package com.omersakalli.veripark

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import com.omersakalli.veripark.databinding.MainActivityBinding
import com.omersakalli.veripark.presentation.di.Injector
import com.omersakalli.veripark.presentation.handshake.HandshakeViewModel
import com.omersakalli.veripark.presentation.handshake.HandshakeViewModelFactory
import com.omersakalli.veripark.presentation.imkblist.ImkbListFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var factory: HandshakeViewModelFactory
    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: HandshakeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        (application as Injector).createHandshakeSubComponent().inject(this)

        viewModel = ViewModelProvider(this, factory).get(HandshakeViewModel::class.java)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.nav_app_bar_open_drawer_description,
            R.string.nav_app_bar_close_drawer_description
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else super.onBackPressed()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val fragment = ImkbListFragment()

        when (item.itemId) {

            R.id.nav_allstocks -> {
                fragment.arguments = bundleOf("period" to "all")
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                    .commit()
            }

            R.id.nav_rising -> {
                fragment.arguments = bundleOf("period" to "increasing")
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                    .commit()
            }

            R.id.nav_falling -> {
                fragment.arguments = bundleOf("period" to "decreasing")
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                    .commit()
            }

            R.id.nav_100 -> {
                fragment.arguments = bundleOf("period" to "volume30")
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                    .commit()
            }

            R.id.nav_50 -> {
                fragment.arguments = bundleOf("period" to "volume50")
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                    .commit()
            }

            R.id.nav_30 -> {
                fragment.arguments = bundleOf("period" to "volume100")
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                    .commit()
            }
        }


        return true
    }

}