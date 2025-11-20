package com.emperormoh.reviveapp.presentation.scan

import android.graphics.Bitmap
import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emperormoh.reviveapp.data.local.AppDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject
import kotlin.math.min

@HiltViewModel
class ScanViewModel @Inject constructor(
    private val appDataStore: AppDataStore
) : ViewModel() {

    private val _uiState = MutableStateFlow(ScanUiState())
    val uiState = _uiState.asStateFlow()

    private var currentBitmap: Bitmap? = null

    fun onImageCaptured(bitmap: Bitmap) {
        // 1. Downscale large images
        val scaledBitmap = downscaleBitmap(bitmap, 1080)

        currentBitmap = scaledBitmap
        _uiState.update { it.copy(previewBitmap = scaledBitmap) }
    }

    fun uploadImage() {
        val bitmap = currentBitmap ?: return

        viewModelScope.launch {
            _uiState.update { it.copy(isUploading = true) }

            val base64 = encodeToBase64(bitmap)

            try {
                //repository.uploadImage(Base64ImageRequest(base64))

                _uiState.update {
                    it.copy(isUploading = false, uploadSuccess = true)
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isUploading = false, uploadError = e.localizedMessage)
                }
            }
        }
    }

    fun uploadImage(bitmap: Bitmap) {
        val base64 = encodeToBase64(bitmap)
        uploadToBackend(base64)
    }

    private fun encodeToBase64(bitmap: Bitmap): String {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        val byteArray = outputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }

    private fun downscaleBitmap(source: Bitmap, maxSize: Int): Bitmap {
        val ratio = min(
            maxSize.toFloat() / source.width,
            maxSize.toFloat() / source.height
        )
        val width = (source.width * ratio).toInt()
        val height = (source.height * ratio).toInt()
        return Bitmap.createScaledBitmap(source, width, height, true)
    }

    private fun uploadToBackend(base64: String) {
        viewModelScope.launch {
            try {
                //repository.uploadImage(Base64ImageRequest(base64))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

data class ScanUiState(
    val previewBitmap: Bitmap? = null,
    val isUploading: Boolean = false,
    val uploadSuccess: Boolean = false,
    val uploadError: String? = null
)