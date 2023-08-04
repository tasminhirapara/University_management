package com.example.myapplication3.Activity

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class ProfileImageManager {



}
//object ProfileImageManager2 {
//
//    fun loadProfileImage(context: Context, url: String, imageView: ImageView) {
//        Glide.with(context)
//            .asBitmap()
//            .load(url)
//            .into(imageView)
//    }
//
//    fun saveProfileImageLocally(context: Context, imageView: ImageView, fileName: String) {
//        val bitmap = (imageView.drawable as? BitmapDrawable)?.bitmap
//        Toast.makeText(context, "1", Toast.LENGTH_SHORT).show()
//        if (bitmap != null) {
//            saveBitmapToInternalStorage(context, bitmap, fileName)
//        }
//    }
//
//    private fun saveBitmapToInternalStorage(context: Context, bitmap: Bitmap, fileName: String) {
//        Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
//        val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ProfileImages")
//        if (!directory.exists()) {
//            directory.mkdirs()
//        }
//
//        val imageFile = File(directory, fileName)
//
//        var outputStream: OutputStream? = null
//        try {
//            outputStream = FileOutputStream(imageFile)
//            Toast.makeText(context, "3", Toast.LENGTH_SHORT).show()
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        } finally {
//            try {
//                outputStream?.close()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//    }
//}
object ProfileImageManager2 {

    fun loadProfileImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context)
            .asBitmap()
            .load(url)
            .into(imageView)
    }

    fun saveProfileImageLocally(context: Context, url: String, fileName: String) {
        val directory = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ProfileImages")
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val imageFile = File(directory, fileName)

        Thread {
            var outputStream: OutputStream? = null
            try {
                val bitmap = Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .submit()
                    .get()

                outputStream = FileOutputStream(imageFile)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    outputStream?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }
}