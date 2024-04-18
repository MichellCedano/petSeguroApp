package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NotasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        val buttonAceptar : Button = findViewById(R.id.btnAceptar)


        buttonAceptar.setOnClickListener {
            var intent: Intent = Intent( this, CalendarioActivity::class.java)
            startActivity(intent)
        }
    }
}