package com.example.sampahcapsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sampahcapsapp.data.UserPreference
import com.example.sampahcapsapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var userPreference: UserPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        userPreference = UserPreference(requireContext())

        // Tampilkan poin pengguna
        val points = userPreference.getPoints()
        binding.pointsTextView.text = "Poin: $points"

        return root
    }

    override fun onResume() {
        super.onResume()
        // Perbarui poin setiap kali fragment ditampilkan
        val points = userPreference.getPoints()
        binding.pointsTextView.text = "Poin: $points"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
