package mx.edu.potros.petseguroapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MenuActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val buttonCalendario : ImageButton = findViewById(R.id.ibCalendario)

        val buttonPreguntasFrecuentes : ImageButton = findViewById(R.id.ibPreguntasFrec)

        val buttonMisMascotas : ImageButton = findViewById(R.id.ibMisMascotas)

        val buttonEntrenamiento : ImageButton = findViewById(R.id.ibEntrenamiento)

        val buttonAgendarCita : ImageButton = findViewById(R.id.ibAgendarCita)

        val buttonCerrarSesion : ImageButton = findViewById(R.id.ibCerrarSesion)

        buttonCalendario.setOnClickListener {
            var intent: Intent = Intent( this, CalendarioActivity::class.java)
            startActivity(intent)
        }

        buttonPreguntasFrecuentes.setOnClickListener {
            var intent: Intent = Intent( this, PreguntasFrecActivity::class.java)
            startActivity(intent)
        }

        buttonMisMascotas.setOnClickListener {
            var intent: Intent = Intent( this, MisMascotasActivity::class.java)
            startActivity(intent)
        }

        buttonEntrenamiento.setOnClickListener {
            var intent: Intent = Intent( this, EntrenamientoActivity::class.java)
            startActivity(intent)
        }

        buttonAgendarCita.setOnClickListener {
            var intent: Intent = Intent( this, AgendarCitaActivity::class.java)
            startActivity(intent)
        }
        buttonCerrarSesion.setOnClickListener {
            var intent: Intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}