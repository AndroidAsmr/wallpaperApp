package com.hadirahimi.wallpaper.ui


import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import coil.load
import com.hadirahimi.wallpaper.data.model.UnsplashPhoto
import com.hadirahimi.wallpaper.data.server.UnsplashApi
import com.hadirahimi.wallpaper.databinding.ItemUnsplashPhotoBinding
import javax.inject.Inject

class UnsplashAdapter @Inject constructor() : PagingDataAdapter<UnsplashPhoto , UnsplashAdapter.PhotoViewHolder>(
    PHOTO_COMPARATOR
) {

    private lateinit var binding : ItemUnsplashPhotoBinding
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder
    {
        binding = ItemUnsplashPhotoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PhotoViewHolder()
    }

    override fun onBindViewHolder(holder: PhotoViewHolder , position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }
    
    override fun getItemViewType(position : Int) : Int
    {
        return position
    }
    
    private var onItemClickListener : ((UnsplashPhoto) -> Unit?)? = null
    
    fun setOnItemClickListener(listener : (UnsplashPhoto) -> Unit)
    {
        onItemClickListener = listener
    }
    
    inner class PhotoViewHolder() : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: UnsplashPhoto?) {
            binding.apply {
                if (photo != null) {
                    imageView.load(photo.urls.small)
                    Log.e("HECTOR" , "small --> "+photo.urls.small +"\n mid --> "  +photo.urls.medium +"\n large --> "+photo.urls.large +"\n ")
                } else {
                    imageView.setImageDrawable(null)
                }
                root.setOnClickListener {
                    onItemClickListener?.let {
                        photo?.let { pic -> it(pic) }
                    }
                }
            }
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<UnsplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnsplashPhoto , newItem: UnsplashPhoto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UnsplashPhoto , newItem: UnsplashPhoto): Boolean {
                return oldItem == newItem
            }
        }
    }
}