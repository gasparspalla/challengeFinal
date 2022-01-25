package com.munidigital.bc2201.challengefinal

import android.annotation.SuppressLint
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
import android.widget.Toast
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

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        geoLocate()
    }

    private fun geoLocate() {
        val location=intent.extras?.getString(LOCATION_KEY)?:"Cordoba,Argentina"
        val searchString = location
        val geocoder = Geocoder(this@MapsActivity)
        var list: List<Address> = ArrayList()
        try {
            list = geocoder.getFromLocationName(searchString, 1)
        } catch (e: IOException) {
            Log.d("ERROR LOCALIZACION",e.toString())
        }
        if (list.size > 0) {
            val address: Address = list[0]
            Log.d("LA LOCALIZACION ES ",address.toString())
            Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();
            val location=LatLng(address.latitude,address.longitude)
            mMap.addMarker(MarkerOptions().position(location).title("Marker in Sydney"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,13f))
        }

    }
}