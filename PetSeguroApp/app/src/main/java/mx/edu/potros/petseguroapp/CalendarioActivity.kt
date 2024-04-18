package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CalendarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario)

        val buttonRegresar : Button = findViewById(R.id.btnRegresar)
        val nota = findViewById<TextView>(R.id.tvNota)


        buttonRegresar.setOnClickListener {
            var intent: Intent = Intent( this, MenuActivity::class.java)
            startActivity(intent)
        }

        nota.setOnClickListener {
            var intent: Intent = Intent( this, NotasActivity::class.java)
            startActivity(intent)

        }
    }
}