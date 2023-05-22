package br.ufu.sisegresso.util

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.*

abstract class TextUtil {
    companion object {
        fun generateRandomString(): String? {
            val uuid: UUID = UUID.randomUUID()

            val byteBuffer: ByteBuffer = ByteBuffer.wrap(ByteArray(16)).apply {
                order(ByteOrder.LITTLE_ENDIAN)
                putLong(uuid.mostSignificantBits)
                putLong(uuid.leastSignificantBits)
            }

            return Base64.getEncoder().encodeToString(byteBuffer.array())
        }
    }
}