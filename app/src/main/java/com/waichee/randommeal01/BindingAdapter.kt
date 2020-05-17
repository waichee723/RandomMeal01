package com.waichee.randommeal01

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso
import com.waichee.randommeal01.detail.MealApiStatus
import com.waichee.randommeal01.network.Ingredient

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
//        Glide.with(imgView.context)
//            .load(imgUrl)
//            .placeholder(R.drawable.loading_animation)
//            .error(R.drawable.ic_broken_image)
//            .into(imgView)

        Picasso.get()
            .load(imgUrl)
            .placeholder(R.drawable.loading_animation)
            .resize(imgView.width, imgView.height)
            .centerCrop()
            .error(R.drawable.ic_broken_image)
            .into(imgView)
    }
}


@BindingAdapter("mealApiStatus")
fun bindStatus(statusImageView: ImageView, status: MealApiStatus?) {
    when (status) {
        MealApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        MealApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_broken_image)
        }
        MealApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}


@BindingAdapter("ingredientName")
fun TextView.setIngredientName(item: Ingredient) {
    text = item.ingredientName
}

@BindingAdapter("ingredientMeasure")
fun TextView.setIngredientMeasure(item: Ingredient) {
    text = item.ingredientMeasure
}