package com.munidigital.bc2201.challengefinal.activities

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.munidigital.bc2201.challengefinal.R
import com.munidigital.bc2201.challengefinal.FavoriteTeam
import com.munidigital.bc2201.challengefinal.TeamArg
import com.munidigital.bc2201.challengefinal.databinding.ActivityMainBinding
import com.munidigital.bc2201.challengefinal.interfaces.SetOnItemListener
import com.munidigital.bc2201.challengefinal.ui.home.HomeFragmentDirections

class MainActivity : AppCompatActivity(), SetOnItemListener {
    private lateinit var binding:ActivityMainBinding
    private lateinit var navController:NavController
    private lateinit var navView:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        navView = binding.navView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_navigation_container) as NavHostFragment
        navController =navHostFragment.findNavController()

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorite, R.id.navigation_session
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    setNavBotton()
    }

    private fun setNavBotton() {
        navController.addOnDestinationChangedListener{controller,destination,argument->
            when(destination.id){
                R.id.navigation_home ->showNavBottom()
                R.id.navigation_favorite ->showNavBottom()
                R.id.detailFragment ->hideNavBottom()
                R.id.navigation_session ->showNavBottom()
            }
        }
    }

    private fun showNavBottom() {
        navView.visibility=View.VISIBLE
    }

    private fun hideNavBottom() {
        navView.visibility=View.GONE
    }



    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.main_navigation_container).navigateUp()
    }

    override fun onTeamSelected(team: TeamArg) {
        findNavController(R.id.main_navigation_container).navigate(HomeFragmentDirections.actionNavigationHomeToDetailFragment(team))

    }

    override fun onFavoriteSelected(favorite: FavoriteTeam) {
        findNavController(R.id.main_navigation_container).navigate(HomeFragmentDirections.actionNavigationHomeToNavigationFavorite(favorite))
    }


}