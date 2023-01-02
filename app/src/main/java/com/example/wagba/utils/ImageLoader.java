package com.example.wagba.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.appcompat.content.res.AppCompatResources;

import com.bumptech.glide.Glide;
import com.example.wagba.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ImageLoader {
    public static void load(Context context, ImageView imageView, String logoPath) {
        if (TextUtils.isEmpty(logoPath)) {
            Glide.with(context)
                    .load(AppCompatResources.getDrawable(context,
                            R.drawable.ic_outline_restaurant_24))
                    .into(imageView);
        }else{
            StorageReference storageReference = FirebaseStorage
                    .getInstance()
                    .getReference(Constant.logos_END_POINT)
                    .child(logoPath);
            Glide.with(context)
                    .load(storageReference)
                    .into(imageView);

        }
    }

}
