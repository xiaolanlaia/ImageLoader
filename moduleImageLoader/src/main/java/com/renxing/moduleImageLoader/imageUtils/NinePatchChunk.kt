package com.renxing.moduleImageLoader.imageUtils

import android.graphics.Rect
import java.lang.RuntimeException
import java.nio.ByteBuffer
import java.nio.ByteOrder

class NinePatchChunk {
    val mPaddings = Rect()
    lateinit var mDivX: IntArray
    lateinit var mDivY: IntArray
    lateinit var mColor: IntArray

    companion object {
        private fun readIntArray(data: IntArray, buffer: ByteBuffer) {
            var i = 0
            val n = data.size
            while (i < n) {
                data[i] = buffer.int
                ++i
            }
        }

        private fun checkDivCount(length: Int) {
            if (length == 0 || length and 0x01 != 0) throw RuntimeException("invalid nine-patch: $length")
        }

        fun deserialize(data: ByteArray): NinePatchChunk? {
            val byteBuffer = ByteBuffer.wrap(data).order(ByteOrder.nativeOrder())
            if (byteBuffer.get().toInt() == 0) return null // is not serialized
            val chunk = NinePatchChunk()
            chunk.mDivX = IntArray(byteBuffer.get().toInt())
            chunk.mDivY = IntArray(byteBuffer.get().toInt())
            chunk.mColor = IntArray(byteBuffer.get().toInt())
            checkDivCount(chunk.mDivX.size)
            checkDivCount(chunk.mDivY.size)

            // skip 8 bytes
            byteBuffer.int
            byteBuffer.int
            chunk.mPaddings.left = byteBuffer.int
            chunk.mPaddings.right = byteBuffer.int
            chunk.mPaddings.top = byteBuffer.int
            chunk.mPaddings.bottom = byteBuffer.int

            // skip 4 bytes
            byteBuffer.int
            readIntArray(chunk.mDivX, byteBuffer)
            readIntArray(chunk.mDivY, byteBuffer)
            readIntArray(chunk.mColor, byteBuffer)
            return chunk
        }
    }
}