package com.bill.billdemo.util

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView
import com.bill.billdemo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.target.BitmapImageViewTarget

/**头像圆形传用*/
@BindingAdapter("iconImageUrl")
fun loadUserIconImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView.getContext())
            .load(imageUrl)
            .asBitmap()
            .placeholder(R.drawable.icon_face_space)
            .error(R.drawable.icon_face_space)
            .into(object : BitmapImageViewTarget(imageView) {
                override fun setResource(resource: Bitmap?) {
                    val circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(imageView.context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            })
}

@BindingAdapter("imageUrl_radius_5dp")
fun loadSmallImage(imageView: ImageView, imageUrl: String?) {


//设置图片圆角角度

    Glide.with(imageView.getContext())
            .load(imageUrl)
            .centerCrop()
//            .transform(CenterCrop(imageView.context), GlideRoundTransform(imageView.context, 5))
            .placeholder(android.R.color.darker_gray)
            .error(R.drawable.icon_face_space)
            .into(imageView)
}
