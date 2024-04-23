package estacio.acad.mobplacar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val splash_time_out: Int = 3000
        Handler().postDelayed({
            // Esse bloco será executado após o tempo especificado
            // e inicia a activity principal
            val i = Intent(this, login::class.java)
            startActivity(i)

            // Fecha esta activity
            finish()
        }, splash_time_out.toLong())



    }
}