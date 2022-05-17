package com.renxing.moduleImageLoader

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import com.renxing.moduleImageLoader.imageUtils.ModuleImageConstant
import com.renxing.moduleImageLoader.loaderStrategy.control.ImageLoaderInterface
import com.renxing.moduleImageLoader.loaderStrategy.control.LoaderStrategyFactory
import com.renxing.moduleImageLoader.loaderStrategy.glide.target.RXCustomTarget

/**
 *@author  :  WuJianFeng
 */
object RXImageLoader : ImageLoaderInterface {
    private var imageLoaderStrategy = LoaderStrategyFactory.instance.getLoaderStrategy()


    override fun loadImage(urlOrId: Any, imageView: ImageView) {
    imageLoaderStrategy.loadImage(urlOrId,imageView)
    }

    override fun loadImage(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadImage(urlOrId,imageView,placeholderImg)
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {
        imageLoaderStrategy.loadImage(url,imageView,width,height)
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int, placeholderImg: Int) {
        imageLoaderStrategy.loadImage(url,imageView,width,height, placeholderImg)
    }

    override fun loadImageWithFitCenter(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithFitCenter(urlOrId,imageView)
    }

    override fun loadImageWithFitCenter(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadImageWithFitCenter(urlOrId,imageView, placeholderImg)
    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrId,imageView)
    }

    override fun loadImageWithCenterCrop(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadImageWithCenterCrop(urlOrId,imageView, placeholderImg)
    }

    override fun loadImageWithCenterInside(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithCenterInside(urlOrId,imageView)
    }

    override fun loadImageWithCenterInside(
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadImageWithCenterInside(urlOrId,imageView, placeholderImg)
    }

    override fun loadImageWithSkipCache(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadImageWithSkipCache(urlOrId,imageView)
    }

    override fun loadImageWithSkipCache(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadImageWithSkipCache(urlOrId,imageView, placeholderImg)
    }

    override fun loadImageWithSkipCache(url: String, imageView: ImageView, width: Int, height: Int) {
        imageLoaderStrategy.loadImageWithSkipCache(url,imageView,width,height)
    }

    override fun loadImageWithSkipCache(
        url: String,
        imageView: ImageView,
        width: Int,
        height: Int,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadImageWithSkipCache(url,imageView,width,height,placeholderImg)
    }

    override fun loadCircleImage(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadCircleImage(urlOrId,imageView)
    }

    override fun loadCircleImage(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadCircleImage(urlOrId,imageView, placeholderImg)
    }

    override fun loadRoundedCornersImage(urlOrId: Any, imageView: ImageView, cornerWidth: Float) {
        imageLoaderStrategy.loadRoundedCornersImage(urlOrId,imageView,cornerWidth)
    }

    override fun loadRoundedCornersImage(
        urlOrId: Any,
        imageView: ImageView,
        cornerWidth: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadRoundedCornersImage(urlOrId,imageView,cornerWidth,placeholderImg)
    }

    override fun loadRoundedCornersImage(urlOrId: Any, imageView: ImageView, cornerWidth: Float, cornerType: ModuleImageConstant.CornerType) {
        imageLoaderStrategy.loadRoundedCornersImage(urlOrId,imageView,cornerWidth,cornerType)
    }

    override fun loadRoundedCornersImage(
        urlOrId: Any,
        imageView: ImageView,
        cornerWidth: Float,
        cornerType: ModuleImageConstant.CornerType,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadRoundedCornersImage(urlOrId,imageView,cornerWidth,cornerType,placeholderImg)
    }

    override fun loadRoundedCornersImage(
        bitmap: Bitmap,
        imageView: ImageView,
        cornerWidth: Float,
        cornerType: ModuleImageConstant.CornerType
    ) {
        imageLoaderStrategy.loadRoundedCornersImage(bitmap,imageView,cornerWidth,cornerType)
    }

    override fun loadRoundedCornersImage(
        bitmap: Bitmap,
        imageView: ImageView,
        cornerWidth: Float,
        cornerType: ModuleImageConstant.CornerType,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadRoundedCornersImage(bitmap,imageView,cornerWidth,cornerType,placeholderImg)
    }

    override fun loadBorderCircleImage(urlOrId: Any, imageView: ImageView, borderColor : Int, borderWidth : Float) {
        imageLoaderStrategy.loadBorderCircleImage(urlOrId,imageView, borderColor, borderWidth)
    }

    override fun loadBorderCircleImage(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCircleImage(urlOrId,imageView, borderColor, borderWidth,placeholderImg)
    }

    override fun loadBorderCornerImage(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerWidth: Float
    ) {
        imageLoaderStrategy.loadBorderCornerImage(urlOrId,imageView, borderColor, borderWidth, cornerWidth)
    }

    override fun loadBorderCornerImage(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerWidth: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCornerImage(urlOrId,imageView, borderColor, borderWidth, cornerWidth,placeholderImg)
    }

    override fun loadGif(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadGif(urlOrId,imageView)
    }

    override fun loadGif(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadGif(urlOrId,imageView,placeholderImg)
    }

    override fun loadGif(playTimes : Int, urlOrId: Any, imageView: ImageView, ) {
        imageLoaderStrategy.loadGif(playTimes,urlOrId,imageView)
    }

    override fun loadGif(playTimes: Int, urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadGif(playTimes, urlOrId,imageView,placeholderImg)
    }

    override fun loadCircleGif(urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadCircleGif(urlOrId,imageView)
    }

    override fun loadCircleGif(urlOrId: Any, imageView: ImageView, placeholderImg: Int) {
        imageLoaderStrategy.loadCircleGif(urlOrId,imageView, placeholderImg)
    }
    override fun loadCircleGif(playTimes: Int,urlOrId: Any, imageView: ImageView) {
        imageLoaderStrategy.loadCircleGif(playTimes,urlOrId,imageView)
    }

    override fun loadCircleGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadCircleGif(playTimes,urlOrId,imageView, placeholderImg)
    }

    override fun loadRoundedCornerGif(urlOrId: Any, imageView: ImageView, cornerWidth: Float) {
        imageLoaderStrategy.loadRoundedCornerGif(urlOrId,imageView, cornerWidth)
    }


    override fun loadRoundedCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        cornerWidth: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadRoundedCornerGif(urlOrId,imageView, cornerWidth,placeholderImg)
    }
    override fun loadRoundedCornerGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        cornerWidth: Float,
    ) {
        imageLoaderStrategy.loadRoundedCornerGif(playTimes,urlOrId,imageView, cornerWidth)
    }

    override fun loadRoundedCornerGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        cornerWidth: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadRoundedCornerGif(playTimes,urlOrId,imageView, cornerWidth, placeholderImg)
    }

    override fun loadBorderCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerWidth: Float
    ) {
        imageLoaderStrategy.loadBorderCornerGif(urlOrId,imageView, borderColor, borderWidth, cornerWidth)
    }

    override fun loadBorderCornerGif(
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerWidth: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCornerGif(urlOrId,imageView, borderColor, borderWidth, cornerWidth,placeholderImg)
    }

    override fun loadBorderCornerGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerWidth: Float
    ) {
        imageLoaderStrategy.loadBorderCornerGif(playTimes,urlOrId,imageView, borderColor, borderWidth, cornerWidth)
    }

    override fun loadBorderCornerGif(
        playTimes: Int,
        urlOrId: Any,
        imageView: ImageView,
        borderColor: Int,
        borderWidth: Float,
        cornerWidth: Float,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadBorderCornerGif(playTimes,urlOrId,imageView, borderColor, borderWidth, cornerWidth, placeholderImg)
    }

    override fun loadImageWithRXCustomTarget(urlOrId: Any, context: Context, rxCustomTarget: RXCustomTarget<Bitmap>) {
        imageLoaderStrategy.loadImageWithRXCustomTarget(urlOrId,context,rxCustomTarget)
    }

    override fun loadImageWithRXCustomTarget(
        urlOrId: Any,
        context: Context,
        rxCustomTarget: RXCustomTarget<Bitmap>,
        placeholderImg: Int
    ) {
        imageLoaderStrategy.loadImageWithRXCustomTarget(urlOrId,context,rxCustomTarget,placeholderImg)
    }

    override fun load9Png(urlOrId: Any, view: View) {
        imageLoaderStrategy.load9Png(urlOrId, view)
    }

    override fun load9Png(urlOrId: Any, view: View, placeholderImg: Int) {
        imageLoaderStrategy.load9Png(urlOrId, view,placeholderImg)
    }
}