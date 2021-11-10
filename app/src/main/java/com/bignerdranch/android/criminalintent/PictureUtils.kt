package com.bignerdranch.android.criminalintent

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point

fun getScaledBitmap(path:String,activity: Activity):Bitmap{
    val size = Point()
    activity.windowManager.defaultDisplay.getSize(size)
    return getScaledBitmap(path,size.x,size.y)
}
fun getScaledBitmap(path: String, destWidt: Int, destHeight: Int): Bitmap {
    //чтение размеров изображения
    var options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(path, options)

    val srcWidth = options.outWidth.toFloat()
    val srcHeight = options.outHeight.toFloat()

    //Выясняем, на сколько необходимо уменьшить
    var inSampleSize = 1
    if (srcHeight>destHeight || srcWidth>destWidt){
        val heightScale = srcHeight/destHeight
        val widthScale = srcWidth/destWidt

        val sampleScale = if(heightScale>widthScale){
            heightScale
        }else{
            widthScale
        }
        inSampleSize = Math.round(sampleScale)
    }
    options = BitmapFactory.Options()
    options.inSampleSize = inSampleSize
    //Чтение и создание окончательного растрового изображения
    return BitmapFactory.decodeFile(path,options)
}