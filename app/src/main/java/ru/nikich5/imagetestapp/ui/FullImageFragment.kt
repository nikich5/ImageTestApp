package ru.nikich5.imagetestapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ru.nikich5.imagetestapp.databinding.FullImageFragmentBinding
import ru.nikich5.imagetestapp.viewmodel.ImageViewModel

@AndroidEntryPoint
class FullImageFragment : Fragment() {
    private val imageViewModel: ImageViewModel by viewModels(ownerProducer = ::requireParentFragment)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FullImageFragmentBinding.inflate(inflater, container, false)
        val image = imageViewModel.currentImage
        hideSystemBars()

        Glide.with(binding.fullImage)
            .load(image.url)
            .fitCenter()
            .timeout(10000)
            .into(binding.fullImage)
        //автоматическое кэширование изображения
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        showSystemBars()
    }

    private fun hideSystemBars() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(activity?.window?.decorView ?: return) ?: return
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
    }
    //Функции для скрытия и отображения navigation bar
    private fun showSystemBars() {
        val windowInsetsController =
            ViewCompat.getWindowInsetsController(activity?.window?.decorView ?: return) ?: return
        windowInsetsController.show(WindowInsetsCompat.Type.navigationBars())
    }
}