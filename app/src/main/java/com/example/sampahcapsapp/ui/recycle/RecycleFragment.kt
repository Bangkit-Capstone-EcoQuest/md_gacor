package com.example.sampahcapsapp.ui.recycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampahcapsapp.R
import com.example.sampahcapsapp.databinding.FragmentRecycleBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class RecycleFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentRecycleBinding? = null
    private val binding get() = _binding!!

    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recycleViewModel =
            ViewModelProvider(this).get(RecycleViewModel::class.java)

        _binding = FragmentRecycleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textRecycle
        recycleViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        // Set LatLng for Jogjakarta
        val jogjakarta = LatLng(-7.797068, 110.370529)
        googleMap.addMarker(MarkerOptions().position(jogjakarta).title("Jogjakarta"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jogjakarta, 12f))

        // Add additional markers for recycling centers around Jogjakarta
        val recyclingCenter1 = LatLng(-7.768096469147989, 110.3510583582836)
        googleMap.addMarker(MarkerOptions().position(recyclingCenter1).title("Waste Processing Center for Independent Tirtasani Residence"))

        val recyclingCenter2 = LatLng(-7.76316137368073, 110.3707171664027)
        googleMap.addMarker(MarkerOptions().position(recyclingCenter2).title("UD Sampah Berkah"))

        // Add more markers as needed
        val recyclingCenter3 = LatLng(-7.801679960033557, 110.3573797524383)
        googleMap.addMarker(MarkerOptions().position(recyclingCenter3).title("Bank Sampah Pokoke Resik"))
    }

    override fun onResume() {
        super.onResume()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment
        mapFragment.onResume()
    }

    override fun onPause() {
        super.onPause()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment
        mapFragment.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment
        mapFragment.onDestroy()
        _binding = null
    }

    override fun onLowMemory() {
        super.onLowMemory()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map_container) as SupportMapFragment
        mapFragment.onLowMemory()
    }
}
