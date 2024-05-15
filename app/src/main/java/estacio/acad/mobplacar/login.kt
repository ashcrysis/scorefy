package estacio.acad.mobplacar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible

class login : AppCompatActivity() {
    var clicked:Boolean = false;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val b_criar_conta: Button = findViewById(R.id.execute_criar_conta)
        val auth = simpleAuth();
        auth.addUser("asher","123456")
        auth.addUser("lucas","12345")
        auth.addUser("","")
        val button: Button = findViewById(R.id.execute_login_button)
        val edit_name: EditText = findViewById(R.id.ed_username)
        val edit_senha: EditText = findViewById(R.id.ed_senha)
        val buttonViewPassword: Button = findViewById(R.id.hide_password)
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


        b_criar_conta.setOnClickListener {

            val intent = Intent(this, cadastro::class.java)
            startActivity(intent);

        }

        buttonViewPassword.setOnClickListener {
            edit_senha.tag = !((edit_senha.tag ?: false) as Boolean)
            edit_senha.inputType = if (edit_senha.tag as Boolean)
                InputType.TYPE_TEXT_VARIATION_PASSWORD
            else
                (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
 }
        }
    }

