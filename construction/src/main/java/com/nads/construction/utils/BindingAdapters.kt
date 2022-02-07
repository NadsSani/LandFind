package com.nads.construction.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


    @BindingAdapter("android:src")
    fun setImage(imageView: ImageView, image: String?) {
        image?.let {
            Glide.with(imageView.context)
                .load(it)
                .centerCrop()
                .into(imageView)
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
    //view.setImageResource(drawableId)
}
