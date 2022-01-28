package com.munidigital.bc2201.challengefinal.activities

import android.content.ContentValues.TAG
import android.content.Intent

import android.location.Address
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.munidigital.bc2201.challengefinal.databinding.ActivityMapsBinding
import android.location.Geocoder
import android.util.Log
import android.widget.ImageView
import androidx.navigation.findNavController
import com.munidigital.bc2201.challengefinal.R
import com.munidigital.bc2201.challengefinal.ui.detail.DetailFragment

import java.io.IOException





class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    companion object{
        const val LOCATION_KEY="location"
    }
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        val bottomBack=findViewById<ImageView>(R.id.imgBack)
        bottomBack.setOnClickListener {
            finish()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        geoLocate()
    }

    private fun geoLocate() {
            val location=intent.extras?.getString(LOCATION_KEY)
            val searchString = location
            val geocoder = Geocoder(this@MapsActivity)
            var list: List<Address> = ArrayList()
            try {
                list = geocoder.getFromLocationName(searchString, 1)
            } catch (e: IOException) {
                Log.e(TAG, "IOException: $e");
            }
            if (list.size > 0) {
                val address: Address = list[0]
                val location=LatLng(address.latitude,address.longitude)
                mMap.addMarker(MarkerOptions().position(location).title(searchString.toString()))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,10.0f))
            }
        }



}