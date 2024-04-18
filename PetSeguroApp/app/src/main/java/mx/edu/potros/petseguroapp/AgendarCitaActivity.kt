package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class AgendarCitaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agendar_cita)

        val buttonRegresar : Button = findViewById(R.id.btnRegresar)

        buttonRegresar.setOnClickListener {
            var intent: Intent = Intent( this, Mascota::class.java)
            startActivity(intent)
        }
    }
}