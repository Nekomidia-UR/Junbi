package godot.junbi.timing

import godot.api.Time
import godot.junbi.necchart.Note

class ChartTimeKeeper(
    private val startTime: Long = Time.getTicksMsec()
) {
    private var pauseTime: Long = 0L
    private var lastPause: Long = 0L
    private var isPaused = false

    fun isNoteReady(note: Note): Boolean =
        startTime + pauseTime + note.appearTime < Time.getTicksMsec()

    fun togglePause(paused: Boolean) {
        isPaused = paused

        if (isPaused) {
            lastPause = Time.getTicksMsec()
        } else {
            pauseTime += Time.getTicksMsec() - lastPause
        }
    }
}