package com.nads.landfind.util

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nads.landfind.adapters.BindableRecyclerViewAdapter
import com.nads.landfind.adapters.LandFinderAdapter
import com.nads.landfind.data.Land
import com.nads.landfind.model.ItemViewModel

@BindingAdapter("itemViewModels")
    fun bindItemViewModels(recyclerView: RecyclerView, itemViewModels: List<ItemViewModel>?) {
        val adapter = getOrCreateAdapter(recyclerView)
        adapter.updateItems(itemViewModels)
    }

    private fun getOrCreateAdapter(recyclerView: RecyclerView): BindableRecyclerViewAdapter {
        return if (recyclerView.adapter != null && recyclerView.adapter is BindableRecyclerViewAdapter) {
            recyclerView.adapter as BindableRecyclerViewAdapter
        } else {
            val bindableRecyclerAdapter = BindableRecyclerViewAdapter()
            recyclerView.adapter = bindableRecyclerAdapter
            bindableRecyclerAdapter
        }
    }
@BindingAdapter("itemViewModel")
fun bindItemViewModel(recyclerView: RecyclerView, itemViewModels: List<ItemViewModel>?) {
    val adapter = getOrCreateAdapters(recyclerView)
    adapter.updateItems(itemViewModels)
}

private fun getOrCreateAdapters(recyclerView: RecyclerView): LandFinderAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is LandFinderAdapter) {
        recyclerView.adapter as LandFinderAdapter
    } else {
        val bindableRecyclerAdapter = LandFinderAdapter(LandFinderAdapter.UserComparator)
        recyclerView.adapter = bindableRecyclerAdapter
        bindableRecyclerAdapter
    }
}
//@BindingAdapter("glideImage")
//fun setGlideImage(view: ImageView?, imageUrl: String?) {
//    GlideApp.with(view?.context?.applicationContext ?: return)
//        .load(imageUrl ?: "").placeholder(R.drawable.place_holder)
//        .into(view)
//}
//
//@BindingAdapter("glideImageId")
//fun setGlideImage(view: ImageView?, imageUrl: Drawable) {
//    GlideApp.with(view?.context?.applicationContext ?: return)
//        .load(imageUrl).placeholder(R.drawable.place_holder)
//        .into(view)
//}
object BindingAdapters {
    @JvmStatic
    @BindingAdapter("android:src")
    fun setImage(imageView: ImageView, image: String?) {
        image?.let {
            Glide.with(imageView.context)
                .load(it)
                .centerCrop()
                .into(imageView)
        }


    }
}



@BindingAdapter("app:srcCompat")
fun srcCompat(view: ImageView, @DrawableRes drawableId: Int) {
    drawableId.let {
        Glide.with(view.context)
            .load(it)
            .fitCenter()
            .into(view)
    }

}
