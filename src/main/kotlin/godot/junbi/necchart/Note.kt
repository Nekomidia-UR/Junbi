package godot.junbi.necchart

import java.io.InputStream

data class Note(
    val appearTime: Long,
    val isHold: Boolean,
    val holdDuration: Long? = null
) {
    companion object {
        fun readFromStream(stream: InputStream): Note {
            val buffer = ByteArray(8)
            var read = stream.readNBytes(buffer, 0, 8)
            if (read != 8) throw IllegalStateException("Not enough bytes read, not proceeding to avoid garbage data")
            val appearTime = ByteDecoders.decodeLongFromByteArray(buffer)

            read = stream.readNBytes(buffer, 0, 1)
            if (read != 1) throw IllegalStateException("Not enough bytes read, not proceeding to avoid garbage data")
            val isHold = ByteDecoders.decodeBooleanFromByte(buffer[0].toUByte())

            return if (isHold) {
                read = stream.readNBytes(buffer, 0, 8)
                if (read != 8) throw IllegalStateException("Not enough bytes read, not proceeding to avoid garbage data")
                val holdDuration = ByteDecoders.decodeLongFromByteArray(buffer)

                Note(appearTime, true, holdDuration)
            } else {
                Note(appearTime, false)
            }
        }
    }
}