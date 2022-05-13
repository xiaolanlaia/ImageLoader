package com.renxing.moduleImageLoader.loaderStrategy.control

import com.renxing.moduleImageLoader.loaderStrategy.glide.ImageLoaderGlide
import java.lang.NullPointerException

class LoaderStrategyFactory private constructor(){
    companion object {
        //对外提供的单例
        val instance: LoaderStrategyFactory by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { LoaderStrategyFactory() }
    }
    //策略Key枚举类
    enum class StrategyTypeEnum { GLIDE, }

    //策略池
    //todo 将所有策略放进缓存池是否导致不必要的内存消耗
    private var loaderStrategyMap = object : HashMap<StrategyTypeEnum, ImageLoaderInterface>(){
        init {
            put(StrategyTypeEnum.GLIDE, ImageLoaderGlide())
        }
    }
    //获取策略
    fun getLoaderStrategy(vararg strategyTypeEnum: StrategyTypeEnum) : ImageLoaderInterface {
        if (strategyTypeEnum.isEmpty()){
            if (loaderStrategyMap[StrategyTypeEnum.GLIDE] == null){
                throw NullPointerException("Glide Strategy is Empty")
            }
            return loaderStrategyMap[StrategyTypeEnum.GLIDE]!!
        }
        if (strategyTypeEnum.size > 1){
            throw IllegalArgumentException("strategyTypeEnum argument error")
        }
        return loaderStrategyMap[strategyTypeEnum[0]]!!
    }
}