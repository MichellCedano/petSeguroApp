package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val buttonIniciarSesion : Button = findViewById(R.id.btnIniciarSesion)

        val buttonRegistro : Button = findViewById(R.id.btnRegistrarme)

        buttonIniciarSesion.setOnClickListener {
            var intent: Intent = Intent( this, MenuActivity::class.java)
            startActivity(intent)
        }

        buttonRegistro.setOnClickListener {
            var intent: Intent = Intent( this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}