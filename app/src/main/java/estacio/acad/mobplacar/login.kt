package estacio.acad.mobplacar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val auth = simpleAuth();
        auth.addUser("asher","123456")
        auth.addUser("lucas","12345")
        val button: Button = findViewById(R.id.execute_login_button)
        val edit_name: EditText = findViewById(R.id.ed_username)
        val edit_senha: EditText = findViewById(R.id.ed_senha)
        button.setOnClickListener {
            val texto_nome: String = edit_name.text.toString()
            val texto_senha: String = edit_senha.text.toString()

            if (auth.verifyCredentials(texto_nome,texto_senha))
            {
                val intent = Intent(this, menu::class.java)
                startActivity(intent);
            }
            else
            {
                val toast = Toast.makeText(this, "Seu nome de usuário ou senha estão incorretos.", Toast.LENGTH_LONG) // in Activity
                toast.show()
            }

        }
    }
}