package estacio.acad.mobplacar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class placar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_placar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val up_buttona: Button = findViewById(R.id.btn_aumentartime_a)
        val down_buttona: Button = findViewById(R.id.btn_diminuirtime_a)

        val up_buttonb: Button = findViewById(R.id.btn_aumentartime_b)
        val down_buttonb: Button = findViewById(R.id.btn_diminuirtime_b)

        val tv_timea: TextView = findViewById(R.id.tv_time_a_pontos)
        val tv_timeb: TextView = findViewById(R.id.tv_time_b_pontos)

        up_buttona.setOnClickListener() {

            var pontos:Int = Integer.parseInt(tv_timea.text.toString())
            pontos = pontos + 1
            tv_timea.setText(pontos)
        }

        down_buttona.setOnClickListener() {

            var pontos:Int = Integer.parseInt(tv_timea.text.toString())
            pontos = pontos - 1
            tv_timea.setText(pontos)
        }
    }
}