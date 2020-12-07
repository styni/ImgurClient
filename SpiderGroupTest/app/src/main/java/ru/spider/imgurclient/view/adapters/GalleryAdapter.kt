package ru.spider.imgurclient.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.spider.imgurclient.R
import ru.spider.imgurclient.databinding.ItemImageGalleryBinding
import ru.spider.imgurclient.model.ImageResponseData
import ru.spider.imgurclient.view.adapters.GalleryAdapter.*


class GalleryAdapter(private val onItemSelected: (item: ImageResponseData.ImageItem) -> Unit) :
    PagingDataAdapter<ImageResponseData.ImageItem, GalleryViewHolder> (COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_gallery, parent, false)
        val binding = ItemImageGalleryBinding.bind(view)

        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class GalleryViewHolder(val binding: ItemImageGalleryBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ImageResponseData.ImageItem) {
            val k = (item.height?.toDouble() ?: 0.0) / (item.width?.toDouble() ?: 1.0)
            val width = binding.item.width
            val height = width * k

            if (height != 0.0) {
                binding.image.layoutParams.height = height.toInt()
                binding.image.requestLayout()
            }

            Glide.with(binding.root)
                .load(item.link)
                .placeholder(R.drawable.ic_baseline_terrain_24)
                .into(binding.image)



            binding.root.setOnClickListener {
                onItemSelected(item)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<ImageResponseData.ImageItem>() {
            override fun areItemsTheSame(oldItem: ImageResponseData.ImageItem, newItem: ImageResponseData.ImageItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ImageResponseData.ImageItem, newItem: ImageResponseData.ImageItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}