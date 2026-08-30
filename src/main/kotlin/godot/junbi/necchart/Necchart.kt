package godot.junbi.necchart

import java.io.File

data class Necchart(
	val header: NecchartHeader,
	val notes: List<NoteArray>
) {
	private val noteOffsets = buildList { repeat(notes.size) { add(0) } }.toMutableList()

	fun nextNote(noteTrack: Int): Note {
		val note = notes[noteTrack][noteOffsets[noteTrack]]
		noteOffsets[noteTrack]++
		return note
	}

	companion object {
		fun readFromFile(file: File): Necchart {
			val inputStream = file.inputStream()

			val header = NecchartHeader.readFromStream(inputStream)
			val notes = buildList {
				repeat(header.buttonCount.toInt()) {
					add(NoteArray.readFromStream(inputStream))
				}
			}

			inputStream.close()

			return Necchart(header, notes)
		}
	}
}
