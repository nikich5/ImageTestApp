package ru.nikich5.imagetestapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import ru.nikich5.imagetestapp.R
import ru.nikich5.imagetestapp.adapter.ImagesAdapter
import ru.nikich5.imagetestapp.adapter.OnInteractionListener
import ru.nikich5.imagetestapp.databinding.FragmentFeedBinding
import ru.nikich5.imagetestapp.dto.Image
import ru.nikich5.imagetestapp.viewmodel.ImageViewModel


@AndroidEntryPoint
class FeedFragment : Fragment() {
    private val imageViewModel: ImageViewModel by viewModels(ownerProducer = ::requireParentFragment)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        val adapter = ImagesAdapter(object : OnInteractionListener {
            override fun clickedOnCard(image: Image) {
                imageViewModel.holdImage(image)
                findNavController().navigate(R.id.action_feedFragment_to_fullImageFragment)
            }
        })
        binding.list.adapter = adapter

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            imageViewModel.data.collectLatest { adapter.submitList(it) }
        }
        return binding.root
    }
}