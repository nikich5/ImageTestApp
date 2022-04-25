package ru.nikich5.imagetestapp.repository

import kotlinx.coroutines.flow.Flow
import ru.nikich5.imagetestapp.dto.Image

interface ImageRepository {
    val data: Flow<List<Image>>
}