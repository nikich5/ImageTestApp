package ru.nikich5.imagetestapp.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.nikich5.imagetestapp.dto.Image
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageRepositoryImpl @Inject constructor(@ApplicationContext appContext: Context) :
    ImageRepository {
    override val data: Flow<List<Image>> = flow {
        val images = mutableListOf<Image>()
        appContext.assets
            .open("images.txt")
            .bufferedReader().forEachLine {
                if (it.startsWith("https://encrypted")) {
                    images.add(Image(it))
                }
            }
        emit(images)
    } //считывание txt файла сохраненного в assets и сохранение только корректных url
}