package com.munidigital.bc2201.challengefinal.activities

import android.content.ContentValues.TAG
import android.content.Context
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
import android.net.ConnectivityManager
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.munidigital.bc2201.challengefinal.IAlert
import com.munidigital.bc2201.challengefinal.R
import java.io.IOException


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,IAlert {

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
        val state_connection=verificateConnection()
        if (state_connection){
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
        else{
            showAlert(R.string.messaggeErrorConnection)
        }


    }

    override fun onPause() {
        super.onPause()
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun verificateConnection():Boolean{
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork= cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    override fun showAlert(id: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.showAlertError))
        builder.setMessage(getString(id))
        builder.setPositiveButton("OK", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}