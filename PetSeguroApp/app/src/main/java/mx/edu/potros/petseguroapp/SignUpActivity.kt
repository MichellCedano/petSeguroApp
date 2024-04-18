package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val buttonRegistro : Button = findViewById(R.id.btnregistrarse)

        buttonRegistro.setOnClickListener {
            var intent: Intent = Intent( this, SignInActivity::class.java)
            startActivity(intent)
        }

    }
}