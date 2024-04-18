package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegistroMascota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_mascota)

        val buttonRegistrar : Button = findViewById(R.id.btnRegistrar)

        val buttonRegresar : Button = findViewById(R.id.btnRegresar)

        buttonRegistrar.setOnClickListener {
            var intent: Intent = Intent( this, RegistroMascota::class.java)
            startActivity(intent)
        }

        buttonRegresar.setOnClickListener {
            var intent: Intent = Intent( this, MisMascotasActivity::class.java)
            startActivity(intent)
        }
    }
}