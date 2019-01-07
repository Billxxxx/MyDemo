package com.bill.billdemo.util

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.widget.ImageView
import android.widget.TextView
import com.arsenal.bill.util.setLeftTagText
import com.bill.billdemo.R
import com.bill.billdemo.entity.PostBean
import com.bill.billdemo.entity.PostType
import com.bumptech.glide.Glide
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
            .placeholder(android.R.color.darker_gray)
            .error(R.drawable.icon_face_space)
            .into(imageView)
}

@BindingAdapter("post_title")
fun loadTitle(textView: TextView, postBean: PostBean) {
    setQATitleWithTag(postBean.type, textView, postBean.title)
}

fun setQATitleWithTag(type: Int, textView: TextView, text: String?) {
    if (text != null && type == PostType.QA.ordinal) {
        textView.setLeftTagText("问", R.color.encourage_pay_dialog, text)
    } else if (text != null && type == PostType.VIDEO.ordinal) {
        textView.setLeftTagText("视", R.color.video_tag_background, text)
    } else {
        textView.text = text
    }
}