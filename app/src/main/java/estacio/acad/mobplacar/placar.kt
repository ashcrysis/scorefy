package estacio.acad.mobplacar

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class placar : AppCompatActivity() {

    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private lateinit var timerTextView: TextView

    var stopTime: Long = 0
    private var startTime: Long = 0
    private var isRunning: Boolean = false
    private val handler = Handler(Looper.getMainLooper())
    private var hasRunned: Boolean = false
    private var elapsedTime: Long = 0
    private var LAST_CLICK_TIME: Long = 0
    private val mDoubleClickInterval = 400 // Milliseconds
    private var stoppedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_placar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val username: String? = intent.getStringExtra("username")

        val button_up_rodada: Button = findViewById(R.id.rodada_up_button)
        val button_down_rodada: Button = findViewById(R.id.rodada_down_button)
        val tv_rodada: TextView = findViewById(R.id.rodada)
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

        val tv_timea: TextView = findViewById(R.id.tv_time_a_pontos)
        val tv_timeb: TextView = findViewById(R.id.tv_time_b_pontos)

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

        // Inicializando os botões e TextView do cronômetro
        startButton = findViewById(R.id.start_button)
        stopButton = findViewById(R.id.stop_button)
        resetButton = findViewById(R.id.reset_button)
        timerTextView = findViewById(R.id.timer_text_view)

        startButton.setOnClickListener {
            startChronometer()
        }
        stopButton.setOnClickListener {
            stopChronometer()
        }
        resetButton.setOnClickListener {
            resetChronometer()
        }

        // Atualizando o cronômetro em intervalos regulares
        handler.post(object : Runnable {
            override fun run() {
                updateChronometer()
                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun startChronometer() {
        if (!isRunning) {
            if (!hasRunned) {
                startTime = System.currentTimeMillis()
                hasRunned = true
            }
            isRunning = true
            startButton.isEnabled = false
            stopButton.isEnabled = true
        }
    }

    private fun stopChronometer() {
        if (isRunning) {
            isRunning = false
            startButton.isEnabled = true
            stopButton.isEnabled = false
            stoppedTime = System.currentTimeMillis()
        }
    }

    private fun resetChronometer() {
        startTime = 0
        elapsedTime = 0
        timerTextView.text = "00:00:00"
        startButton.isEnabled = true
        startTime = System.currentTimeMillis()
    }

    private fun updateChronometer() {
        if (isRunning) {
            elapsedTime = System.currentTimeMillis() - startTime
            val seconds = (elapsedTime / 1000).toInt()
            val minutes = seconds / 60
            var hours = (minutes / 60)
            var displaySeconds = (seconds % 60)
            var displayMinutes = (minutes % 60)

            timerTextView.text = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, displayMinutes, displaySeconds)
        }

    }
}
