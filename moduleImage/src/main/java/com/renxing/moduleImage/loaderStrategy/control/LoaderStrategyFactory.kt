package com.renxing.moduleImage.loaderStrategy.control

import com.renxing.moduleImage.ImageLoaderInterface
import com.renxing.moduleImage.loaderStrategy.glide.ImageLoaderGlide

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
            put(StrategyTypeEnum.GLIDE, ImageLoaderGlide)
        }
    }
    //获取策略
    fun getLoaderStrategy(vararg strategyTypeEnum: StrategyTypeEnum) : ImageLoaderInterface {
        if (strategyTypeEnum.isEmpty()){
            return loaderStrategyMap[StrategyTypeEnum.GLIDE]!!
        }
        if (strategyTypeEnum.size > 1){
            throw IllegalArgumentException("strategyTypeEnum argument error")
        }
        return loaderStrategyMap[strategyTypeEnum[0]]!!
    }




}