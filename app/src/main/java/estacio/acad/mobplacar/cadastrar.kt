package estacio.acad.mobplacar

import UserDatabaseHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class cadastrar : AppCompatActivity() {
    private lateinit var dbHelper: UserDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)
        dbHelper = UserDatabaseHelper(this)

        val editTextUsername: EditText = findViewById(R.id.ed_username)
        val editTextPassword: EditText = findViewById(R.id.ed_senha)
        val registerButton: Button = findViewById(R.id.execute_criar_conta)

        registerButton.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                val result = dbHelper.addUser(username, password)
                if (result > -1) {
                    Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}