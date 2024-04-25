package estacio.acad.mobplacar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
        val button: Button = findViewById(R.id.botaoaumentarnumero)
        val intent: Intent = getIntent()
        val texto: String = intent.getStringExtra("teste").toString()
        button.setOnClickListener {

            val toast =
                Toast.makeText(this, texto, Toast.LENGTH_LONG) // in Activity
            toast.show()

        }


    }
}