package estacio.acad.mobplacar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class placar : AppCompatActivity() {

    private var LAST_CLICK_TIME: Long = 0
    private val mDoubleClickInterval = 400 // Milliseconds


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
        //val button_finalizar_rodada: Button = findViewById(R.id.btn_finalizar)
        val tv_rodada: TextView = findViewById(R.id.rodada)
        button_up_rodada.setOnClickListener()
        {

            var Rodada = Integer.parseInt(tv_rodada.text.toString())
            Rodada += 1
            tv_rodada.text = Rodada.toString()

        }
        button_down_rodada.setOnClickListener()
        {

            var Rodada = Integer.parseInt(tv_rodada.text.toString())
            Rodada -= 1
            tv_rodada.text = Rodada.toString()

        }
        /* button_finalizar_rodada.setOnClickListener()
        {
            val intent = Intent(this, menu::class.java)
            intent.putExtra("username" , username )
            startActivity(intent);
        } */
        val up_buttona: Button = findViewById(R.id.btn_aumentartime_a)
        val down_buttona: Button = findViewById(R.id.btn_diminuirtime_a)

        val up_buttonb: Button = findViewById(R.id.btn_aumentartime_b)
        val down_buttonb: Button = findViewById(R.id.btn_diminuirtime_b)

        val tv_timea: TextView = findViewById(R.id.tv_time_a_pontos)
        val tv_timeb: TextView = findViewById(R.id.tv_time_b_pontos)

        up_buttona.setOnClickListener() {

            var pontos: Int = Integer.parseInt(tv_timea.text.toString())
            pontos += 1
            tv_timea.text = pontos.toString()
        }

        down_buttona.setOnClickListener() {

            var pontos: Int = Integer.parseInt(tv_timea.text.toString())
            pontos -= 1
            tv_timea.text = pontos.toString()
        }

        up_buttonb.setOnClickListener() {

            var pontos: Int = Integer.parseInt(tv_timeb.text.toString())
            pontos += 1
            tv_timeb.text = pontos.toString()
        }

        down_buttonb.setOnClickListener() {

            var pontos: Int = Integer.parseInt(tv_timeb.text.toString())
            pontos -= 1
            tv_timeb.text = pontos.toString()
        }


        lateinit var contador: TextView
        lateinit var start: Button
        lateinit var pausar: Button
        lateinit var resetar: Button

        var handler = Handler()
        var startTime = 0L
        var timeInMilliseconds = 0L
        var timeSwapBuff = 0L
        var updatedTime = 0L

        val updateTimerRunnable = object : Runnable {
            override fun run() {
                timeInMilliseconds = SystemClock.uptimeMillis() - startTime
                updatedTime = timeSwapBuff + timeInMilliseconds

                val secs = (updatedTime / 1000).toInt()
                val mins = secs / 60
                val hours = mins / 60
                val milliseconds = (updatedTime % 1000).toInt()

                contador.text = String.format(LOCALE_SERVICE, hours, mins % 60, secs % 60)
                println(contador)

                handler.postDelayed(this, 0)
            }
        }

        fun onCreate(savedInstanceState: Bundle?) {
            onCreate(savedInstanceState)
            setContentView(R.layout.activity_placar)

            contador = findViewById(R.id.contador)
            start = findViewById(R.id.start)
            pausar = findViewById(R.id.pausar)
            resetar = findViewById(R.id.resetar)

            start.setOnClickListener {
                startTime = SystemClock.uptimeMillis()
                handler.postDelayed(updateTimerRunnable, 0)
                start.isEnabled = false
                pausar.isEnabled = true
                resetar.isEnabled = true
            }

            pausar.setOnClickListener {
                timeSwapBuff += timeInMilliseconds
                handler.removeCallbacks(updateTimerRunnable)
                start.isEnabled = true
                pausar.isEnabled = false
            }

            resetar.setOnClickListener {
                startTime = 0L
                timeInMilliseconds = 0L
                timeSwapBuff = 0L
                updatedTime = 0L
                handler.removeCallbacks(updateTimerRunnable)
                contador.text = "00:00:00"
                start.isEnabled = true
                pausar.isEnabled = false
                resetar.isEnabled = false
            }

            start.isEnabled = true
            pausar.isEnabled = false
            resetar.isEnabled = false

        }
    }


}

