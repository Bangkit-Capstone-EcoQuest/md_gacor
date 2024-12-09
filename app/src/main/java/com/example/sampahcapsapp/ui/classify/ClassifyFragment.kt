package com.example.sampahcapsapp.ui.classify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampahcapsapp.R
import com.example.sampahcapsapp.databinding.FragmentClassifyBinding

class ClassifyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classify, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val startScanningButton = view.findViewById<Button>(R.id.start_scanning_button)
        startScanningButton.setOnClickListener {
            // Handle start scanning logic
            Toast.makeText(requireContext(), "Scanning started!", Toast.LENGTH_SHORT).show()
        }
    }
}

