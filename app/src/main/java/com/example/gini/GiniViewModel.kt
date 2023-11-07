package com.example.gini
import androidx.lifecycle.LiveData

import androidx.lifecycle.ViewModel
import com.example.gini.data.model.ImageEntity
import com.example.gini.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GiniViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    val allImages: LiveData<List<ImageEntity>> = imageRepository.allImages

    init {
        imageRepository.refreshImageList()
    }
}

