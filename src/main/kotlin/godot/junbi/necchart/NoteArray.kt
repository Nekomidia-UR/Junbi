package godot.junbi.necchart

import java.io.InputStream

data class NoteArray(val notes: Array<Note>) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (javaClass != other?.javaClass) return false

		other as NoteArray

		return notes.contentEquals(other.notes)
	}

	override fun hashCode(): Int {
		return notes.contentHashCode()
	}

	operator fun get(index: Int) = notes[index]
	operator fun set(index: Int, element: Note) {
		notes[index] = element
	}

	companion object {
		fun readFromStream(stream: InputStream): NoteArray {
			val noteCountBuffer = ByteArray(4)
			val read = stream.readNBytes(noteCountBuffer, 0, 4)
			if (read != 4) throw IllegalStateException("Not enough bytes read, not proceeding to avoid garbage data")

			val noteCount = ByteDecoders.decodeIntFromByteArray(noteCountBuffer)
			val notes = mutableListOf<Note>()

			repeat(noteCount) {
				notes.add(Note.readFromStream(stream))
			}

			return NoteArray(notes.toTypedArray())
		}
	}
}
