package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Mascota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mascota)

        val buttonAgendar : Button = findViewById(R.id.btnAgendar)
        val buttonRegresar : Button = findViewById(R.id.btnRegresar)

        buttonRegresar.setOnClickListener {
            var intent: Intent = Intent( this, MisMascotasActivity::class.java)
            startActivity(intent)
        }

        buttonAgendar.setOnClickListener {
            var intent: Intent = Intent( this, AgendarCitaActivity::class.java)
            startActivity(intent)
        }
    }
}