package estacio.acad.mobplacar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import estacio.acad.mobplacar.model.Log
import estacio.acad.mobplacar.model.Logs

class menu : AppCompatActivity() {
    private var logAdapter: logAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        val rv = findViewById<RecyclerView>(R.id.recyclerView)
        val logs: List<Log> = Logs.fakeLog()
        logAdapter = logAdapter(logs)
        rv.adapter = logAdapter

        val button: Button = findViewById(R.id.new_game_button)

        button.setOnClickListener {
            val intent = Intent(this, placar::class.java)
            intent.putExtra("teste" , "Você clicou no botão.." )
            startActivity(intent);
        }
    }
}