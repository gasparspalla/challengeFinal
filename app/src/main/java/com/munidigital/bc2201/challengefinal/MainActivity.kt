package com.munidigital.bc2201.challengefinal

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SearchView
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.munidigital.bc2201.challengefinal.databinding.ActivityMainBinding
import com.munidigital.bc2201.challengefinal.ui.home.HomeFragment
import com.munidigital.bc2201.challengefinal.ui.home.HomeFragmentDirections

class MainActivity : AppCompatActivity(),IFragment {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.main_navigation_container) as NavHostFragment
        val navController =navHostFragment.findNavController()


        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_favorite,R.id.navigation_session
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun setTeamSelected(teamArg: TeamArg) {
        findNavController(R.id.main_navigation_container).navigate(HomeFragmentDirections.actionNavigationHomeToDetailFragment(teamArg))
    }

    override fun setFavoriteItem(favoriteTeam:FavoriteTeam) {
        findNavController(R.id.main_navigation_container).navigate(HomeFragmentDirections.actionNavigationHomeToNavigationFavorite(favoriteTeam))
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}