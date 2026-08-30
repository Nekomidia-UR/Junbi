package godot.junbi.necchart

import java.io.File
import java.io.InputStream

data class NecchartHeader(
	val buttonCount: UByte,
	val songName: String,
	val songArtistName: String,
	val bpm: UInt
) {
	companion object {
		fun readFromStream(stream: InputStream): NecchartHeader {
			val magic = String(stream.readNBytes(4))
			if (magic != "NEKO") throw IllegalArgumentException("Header is not valid Nekochart header")

			val version = stream.readNBytes(1)[0].toUByte()
			if (version != 0.toUByte()) throw IllegalArgumentException("Nekochart version is incompatible, got $version, expected 0")

			val type = stream.readNBytes(1)[0].toUByte()
			if (type != 0.toUByte()) throw IllegalArgumentException("Nekochart has incorrect type, expected 0, got $type")

			val buttonCount: UByte = stream.readNBytes(1)[0].toUByte()
			if (buttonCount < 1.toUByte()) throw IllegalArgumentException("There must be at least 1 button in a chart")

			val bpm: UInt = ByteDecoders.decodeIntFromByteArray(stream.readNBytes(4)).toUInt()

			val songNameLength = ByteDecoders.decodeIntFromByteArray(stream.readNBytes(4))
			val songName = String(stream.readNBytes(songNameLength))

			val songArtistNameLength = ByteDecoders.decodeIntFromByteArray(stream.readNBytes(4))
			val songArtistName = String(stream.readNBytes(songArtistNameLength))

			return NecchartHeader(buttonCount, songName, songArtistName, bpm)
		}
	}

	fun readFromFile(file: File): NecchartHeader {
		val inputStream = file.inputStream()
		val header = readFromStream(inputStream)
		inputStream.close()
		return header
	}
}
