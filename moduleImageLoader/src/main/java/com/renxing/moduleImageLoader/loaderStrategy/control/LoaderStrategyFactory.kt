package com.renxing.moduleImageLoader.loaderStrategy.control

import com.renxing.moduleImageLoader.loaderStrategy.glide.ImageLoaderGlide
import java.lang.NullPointerException

/**
 *@author  :  WuJianFeng
 */
class LoaderStrategyFactory private constructor(){
    companion object {
        //对外提供的单例
        val instance: LoaderStrategyFactory by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { LoaderStrategyFactory() }
    }
    //策略Key枚举类
    enum class StrategyTypeEnum { GLIDE, }

    //策略池
    private var loaderStrategyMap = object : HashMap<StrategyTypeEnum, ImageLoaderInterface>(){
        init {
            put(StrategyTypeEnum.GLIDE, ImageLoaderGlide())
        }
    }
    //获取默认策略
    fun getLoaderStrategy() : ImageLoaderInterface {
        if (loaderStrategyMap[StrategyTypeEnum.GLIDE] == null){
            throw NullPointerException("Glide Strategy is Empty")
        }
        return loaderStrategyMap[StrategyTypeEnum.GLIDE]!!
    }
    //获取策略
    fun getLoaderStrategy(strategyTypeEnum: StrategyTypeEnum) : ImageLoaderInterface {
        if (loaderStrategyMap[strategyTypeEnum] == null){
            throw NullPointerException("Strategy is Empty")
        }
        return loaderStrategyMap[strategyTypeEnum]!!
    }
}