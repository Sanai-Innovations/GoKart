package com.sanai.gokart.presentation.activities.dashboard

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sanai.gokart.R
import com.sanai.gokart.data.util.Resource
import com.sanai.gokart.databinding.ActivityDashboardBinding
import com.sanai.gokart.presentation.activities.base.BaseActivity
import com.sanai.gokart.presentation.util.logging.Logger
import com.sanai.gokart.presentation.viewmodel.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity() {

    private lateinit var viewModel: DashboardViewModel
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        bindObservers()

        // get data from server
        viewModel.getDashboardData()
    }

    private fun bindObservers() {
        viewModel.dashboardResponse.observe(this) {
            when (it) {
                is Resource.Error -> {
                    Logger.e(it.message.toString())
                }

                is Resource.Loading -> {
                    Logger.e("Loading")
                }

                is Resource.Success -> {
                    Logger.e("Success $it")
                }
            }
        }
    }

    private fun initVariables() {
        val toolbar: Toolbar = binding.toolbar
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_dashboard)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_account,
                R.id.navigation_cart,
                R.id.navigation_notifications,
                R.id.navigation_more
            )
        )
        toolbar.setupWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // get view model
        viewModel = ViewModelProvider.create(this)[DashboardViewModel::class]
    }
}