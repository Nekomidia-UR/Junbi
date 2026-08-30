package godot.junbi.necchart

object ByteDecoders {
	fun decodeLongFromByteArray(byteArray: ByteArray): Long {
		val final = 0L

		for (byte in byteArray) {
			final shl 8
			final or byte.toUByte().toLong()
		}

		return final
	}

	fun decodeIntFromByteArray(byteArray: ByteArray): Int {
		val final = 0

		for (byte in byteArray) {
			final shl 8
			final or byte.toUByte().toInt()
		}

		return final
	}

	fun decodeBooleanFromByte(byte: UByte): Boolean = byte.toUInt() == 0xFFu
}
