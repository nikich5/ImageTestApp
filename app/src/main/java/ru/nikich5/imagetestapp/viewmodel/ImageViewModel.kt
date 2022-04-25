package ru.nikich5.imagetestapp.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.nikich5.imagetestapp.dto.Image
import ru.nikich5.imagetestapp.repository.ImageRepository
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val repository: ImageRepository) : ViewModel() {
    val data = repository.data

    var currentImage = Image("")
    fun holdImage(image: Image) {
        currentImage = image
    }
}