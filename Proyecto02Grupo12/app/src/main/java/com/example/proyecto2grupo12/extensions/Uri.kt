package com.example.proyecto2grupo12.extensions

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import com.example.proyecto2grupo12.domain.profile.entity.DevicePicture

fun Uri.toBitmap(contentResolver: ContentResolver): Bitmap {
    return if(Build.VERSION.SDK_INT < 28) {
        MediaStore.Images.Media.getBitmap(
            contentResolver,
            this
        )
    } else {
        val source = ImageDecoder.createSource(contentResolver, this)
        ImageDecoder.decodeBitmap(source)
    }
}

fun Uri.toDevicePicture(contentResolver: ContentResolver): DevicePicture {
    return DevicePicture(this, this.toBitmap(contentResolver))
}