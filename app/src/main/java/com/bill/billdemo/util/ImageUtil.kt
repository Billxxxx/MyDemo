package com.bill.billdemo.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bill.billdemo.R
import com.bumptech.glide.Glide


@BindingAdapter("iconImageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.getContext())
            .load(imageUrl)
            .placeholder(R.drawable.icon_face_space)
            .error(R.drawable.icon_face_space)
            .into(view)
}