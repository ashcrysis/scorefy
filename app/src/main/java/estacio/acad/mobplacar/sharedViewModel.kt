package estacio.acad.mobplacar

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var timerText: String = "00:00:00"
    var rodada: Int = 0
    var timeAPoints: Int = 0
    var timeBPoints: Int = 0
    var isRunning: Boolean = false
    var startTime: Long = 0
    var elapsedTime: Long = 0
    var hasRunned: Boolean = false
}