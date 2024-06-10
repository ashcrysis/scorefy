package estacio.acad.mobplacar

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import java.util.Locale

class placar : AppCompatActivity() {

    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private lateinit var timerTextView: TextView
    private lateinit var tv_rodada: TextView
    private lateinit var tv_timea: TextView
    private lateinit var tv_timeb: TextView

    private val sharedViewModel: SharedViewModel by viewModels()

    private var stopTime: Long = 0
    private var startTime: Long = 0
    private var isRunning: Boolean = false
    private val handler = Handler(Looper.getMainLooper())
    private var hasRunned: Boolean = false
    private var elapsedTime: Long = 0
    private var LAST_CLICK_TIME: Long = 0
    private val mDoubleClickInterval = 400 // Milliseconds
    private var stoppedTime: Long = 0 // Removido o duplicate declaration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_placar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeUI()
        loadViewModelData()

        // Start updating the chronometer
        handler.post(object : Runnable {
            override fun run() {
                updateChronometer()
                handler.postDelayed(this, 1000)
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveViewModelData()
    }

    private fun loadViewModelData() {
        timerTextView.text = sharedViewModel.timerText
        tv_rodada.text = sharedViewModel.rodada.toString()
        tv_timea.text = sharedViewModel.timeAPoints.toString()
        tv_timeb.text = sharedViewModel.timeBPoints.toString()
        isRunning = sharedViewModel.isRunning
        startTime = sharedViewModel.startTime
        elapsedTime = sharedViewModel.elapsedTime
        hasRunned = sharedViewModel.hasRunned

        if (isRunning) {
            startChronometer()
        }
    }

    private fun saveViewModelData() {
        sharedViewModel.timerText = timerTextView.text.toString()
        sharedViewModel.rodada = tv_rodada.text.toString().toInt()
        sharedViewModel.timeAPoints = tv_timea.text.toString().toInt()
        sharedViewModel.timeBPoints = tv_timeb.text.toString().toInt()
        sharedViewModel.isRunning = isRunning
        sharedViewModel.startTime = startTime
        sharedViewModel.elapsedTime = elapsedTime
        sharedViewModel.hasRunned = hasRunned
    }

    private fun initializeUI() {
        // Initialize UI elements and listeners
        startButton = findViewById(R.id.start_button)
        stopButton = findViewById(R.id.stop_button)
        resetButton = findViewById(R.id.reset_button)
        timerTextView = findViewById(R.id.timer_text_view)

        tv_rodada = findViewById(R.id.rodada)
        tv_timea = findViewById(R.id.tv_time_a_pontos)
        tv_timeb = findViewById(R.id.tv_time_b_pontos)

        startButton.setOnClickListener {
            startChronometer()
        }
        stopButton.setOnClickListener {
            stopChronometer()
        }
        resetButton.setOnClickListener {
            resetChronometer()
        }

        val button_up_rodada: Button = findViewById(R.id.rodada_up_button)
        val button_down_rodada: Button = findViewById(R.id.rodada_down_button)

        button_up_rodada.setOnClickListener {
            var Rodada = Integer.parseInt(tv_rodada.text.toString())
            Rodada += 1
            tv_rodada.text = Rodada.toString()
        }

        button_down_rodada.setOnClickListener {
            var Rodada = Integer.parseInt(tv_rodada.text.toString())
            Rodada -= 1
            tv_rodada.text = Rodada.toString()
        }

        val up_buttona: Button = findViewById(R.id.btn_aumentartime_a)
        val down_buttona: Button = findViewById(R.id.btn_diminuirtime_a)

        val up_buttonb: Button = findViewById(R.id.btn_aumentartime_b)
        val down_buttonb: Button = findViewById(R.id.btn_diminuirtime_b)

        up_buttona.setOnClickListener {
            var pontos: Int = Integer.parseInt(tv_timea.text.toString())
            pontos += 1
            tv_timea.text = pontos.toString()
        }

        down_buttona.setOnClickListener {
            var pontos: Int = Integer.parseInt(tv_timea.text.toString())
            pontos -= 1
            tv_timea.text = pontos.toString()
        }

        up_buttonb.setOnClickListener {
            var pontos: Int = Integer.parseInt(tv_timeb.text.toString())
            pontos += 1
            tv_timeb.text = pontos.toString()
        }
        down_buttonb.setOnClickListener {
            var pontos: Int = Integer.parseInt(tv_timeb.text.toString())
            pontos -= 1
            tv_timeb.text = pontos.toString()
        }
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        saveViewModelData()
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_placar)
        }

        initializeUI()
        loadViewModelData()
    }
    private var pausedTime: Long = 0
    private var pausedTimerStartTime: Long = 0
    private var pausedTimerElapsedTime: Long = 0
    private var pausedTimerIsRunning: Boolean = false

    private fun startPausedTimer() {
        if (!pausedTimerIsRunning) {
            pausedTimerStartTime = System.currentTimeMillis()
            pausedTimerIsRunning = true
        }
    }

    private fun stopPausedTimer() {
        if (pausedTimerIsRunning) {
            pausedTimerIsRunning = false
            pausedTimerElapsedTime += System.currentTimeMillis() - pausedTimerStartTime
        }
    }

    private fun resetPausedTimer() {
        pausedTimerStartTime = 0
        pausedTimerElapsedTime = 0
        pausedTimerIsRunning = false
    }

    private fun updatePausedTimer() {
        if (pausedTimerIsRunning) {
            pausedTime = System.currentTimeMillis() - pausedTimerStartTime + pausedTimerElapsedTime
        } else {
            pausedTime = pausedTimerElapsedTime
        }
    }

    private fun startChronometer() {
        if (!isRunning) {
            if (!hasRunned) {
                startTime = System.currentTimeMillis()
                hasRunned = true
            } else {
                startPausedTimer()
            }
            isRunning = true
            startButton.isEnabled = false
            stopButton.isEnabled = true
        }
    }

    private fun stopChronometer() {
        if (isRunning) {
            isRunning = false
            stopButton.isEnabled = false
            stoppedTime = System.currentTimeMillis()
            stopPausedTimer()
        }
    }

    private fun resetChronometer() {
        startTime = 0
        elapsedTime = 0
        timerTextView.text = "00:00:00"
        startButton.isEnabled = true
        startTime = System.currentTimeMillis()
        resetPausedTimer()
    }

    private fun updateChronometer() {
        if (isRunning) {
            val currentTime = System.currentTimeMillis()
            elapsedTime = currentTime - startTime - pausedTime
            val seconds = (elapsedTime / 1000).toInt()
            val minutes = seconds / 60
            val hours = minutes / 60
            val displaySeconds = (seconds % 60)
            val displayMinutes = (minutes % 60)

            timerTextView.text = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, displayMinutes, displaySeconds)
        }
    }

}
