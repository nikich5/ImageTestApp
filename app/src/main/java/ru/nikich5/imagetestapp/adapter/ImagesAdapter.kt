package ru.nikich5.imagetestapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.nikich5.imagetestapp.databinding.CardImageBinding
import ru.nikich5.imagetestapp.dto.Image

interface OnInteractionListener {
    fun clickedOnCard(image: Image) {}
}

class ImagesAdapter(private val onInteractionListener: OnInteractionListener) :
    ListAdapter<Image, ImageViewHolder>(ImageDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = CardImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position) ?: return
        holder.bind(image)
    }

}

class ImageViewHolder(
    private val binding: CardImageBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(image: Image) {
        Glide.with(binding.image)
            .load(image.url)
            .fitCenter()
            .timeout(10_000)
            .into(binding.image) //автоматическое кэшироание изображений
        binding.card.setOnClickListener {
            onInteractionListener.clickedOnCard(image)
        }
    }
}

class ImageDiffCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}