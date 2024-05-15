package estacio.acad.mobplacar

import android.os.Bundle
import android.os.Handler
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

        var startTime: Long = 0
        //var timerHandler: Long = 0
        var timerRunnable: Long = 0
        var wireWidgets: Long = 0

        startTime = System.currentTimeMillis()
        val timerHandler = Handler()
        //timerHandler.postDelayed(timerRunnable, 0)

        //#wireWidgets()



        val button_rodada: Button = findViewById(R.id.rodada_button)
        val tv_rodada: TextView = findViewById(R.id.rodada)
        button_rodada.setOnClickListener()
        {
            val doubleClickCurrentTime = System.currentTimeMillis()
            val currentClickTime = System.currentTimeMillis()
            var Rodada_inLoop = Integer.parseInt(tv_rodada.text.toString())
            if (currentClickTime - LAST_CLICK_TIME <= mDoubleClickInterval) {
                Rodada_inLoop -= 1

            } else if (currentClickTime - LAST_CLICK_TIME > mDoubleClickInterval){
                LAST_CLICK_TIME = System.currentTimeMillis()
                Rodada_inLoop += 1
            }
            tv_rodada.text = Rodada_inLoop.toString()

        }
        val up_buttona: Button = findViewById(R.id.btn_aumentartime_a)
        val down_buttona: Button = findViewById(R.id.btn_diminuirtime_a)

        val up_buttonb: Button = findViewById(R.id.btn_aumentartime_b)
        val down_buttonb: Button = findViewById(R.id.btn_diminuirtime_b)

        val tv_timea: TextView = findViewById(R.id.tv_time_a_pontos)
        val tv_timeb: TextView = findViewById(R.id.tv_time_b_pontos)

        up_buttona.setOnClickListener() {

            var pontos:Int = Integer.parseInt(tv_timea.text.toString())
            pontos += 1
            tv_timea.text = pontos.toString()
        }

        down_buttona.setOnClickListener() {

            var pontos:Int = Integer.parseInt(tv_timea.text.toString())
            pontos -= 1
            tv_timea.text = pontos.toString()
        }

        up_buttonb.setOnClickListener() {

            var pontos:Int = Integer.parseInt(tv_timeb.text.toString())
            pontos += 1
            tv_timeb.text = pontos.toString()
        }

        down_buttonb.setOnClickListener() {

            var pontos:Int = Integer.parseInt(tv_timeb.text.toString())
            pontos -= 1
            tv_timeb.text = pontos.toString()
        }
        }
    }
