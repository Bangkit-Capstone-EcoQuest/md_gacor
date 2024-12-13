package com.example.sampahcapsapp.ui.classify

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampahcapsapp.R
import com.example.sampahcapsapp.data.UserPreference
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class ClassifyFragment : Fragment() {

    private lateinit var viewModel: ClassifyViewModel
    private var selectedImageFile: File? = null
    private lateinit var userPreference: UserPreference

    private val imagePicker =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val file = uriToFile(it)
                val resizedFile = viewModel.resizeImage(file)
                selectedImageFile = resizedFile
                Log.d("ClassifyFragment", "Selected Image File: ${selectedImageFile?.path}")
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_classify, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ClassifyViewModel::class.java)
        userPreference = UserPreference(requireContext())

        val uploadButton = view.findViewById<Button>(R.id.start_scanning_button)
        uploadButton.setOnClickListener {
            if (selectedImageFile != null) {
                viewModel.uploadImage(selectedImageFile!!)
                observeResult()
            } else {
                Toast.makeText(requireContext(), "Please select an image first", Toast.LENGTH_SHORT).show()
            }
        }

        val selectImageButton = view.findViewById<Button>(R.id.select_image_button)
        selectImageButton.setOnClickListener {
            imagePicker.launch("image/*")
        }
    }

    private fun uriToFile(uri: Uri): File {
        return try {
            val contentResolver = requireContext().contentResolver
            val inputStream: InputStream = contentResolver.openInputStream(uri)!!
            val tempFile = File.createTempFile("temp_image", ".jpg", requireContext().cacheDir)
            val outputStream = FileOutputStream(tempFile)
            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            tempFile
        } catch (e: Exception) {
            Log.e("ClassifyFragment", "Error converting URI to file", e)
            throw e
        }
    }

    private fun observeResult() {
        viewModel.predictResult.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                Log.d("ClassifyFragment", "Observed Result: $result")
                val intent = Intent(requireContext(), ResultActivity::class.java)
                intent.putExtra("imageFile", selectedImageFile?.path)
                intent.putExtra("className", result.predicted_class)
                intent.putExtra("confidence", result.confidence)
                startActivity(intent)

                // Tambahkan poin setelah scan berhasil
                val currentPoints = userPreference.getPoints()
                userPreference.savePoints(currentPoints + 1)
                Toast.makeText(requireContext(), "Scan berhasil! Poin Anda: ${currentPoints + 1}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Prediction failed. Try again later.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
