package estacio.acad.mobplacar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        val username: String? = intent.getStringExtra("username")
        val button: Button = findViewById(R.id.new_game_button)
        val tv_username: TextView = findViewById(R.id.tv_user)
        tv_username.text = username + "."
        button.setOnClickListener {
            val intent = Intent(this, placar_vertical::class.java)
            intent.putExtra("username" , username )
            startActivity(intent);
        }
    }
}