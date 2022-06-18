package com.wentao.githubapp.image

import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageHelper {
    fun showImage(imageView: ImageView?, url: String?) {
        imageView?.let {
            if (url == null || url.isEmpty()) {
                return
            }
            //
            Glide.with(imageView).load(url).into(imageView)
        }
    }

}