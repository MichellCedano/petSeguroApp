package mx.edu.potros.petseguroapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth

class MenuActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // Obtener el Intent que inició esta actividad
        val intent1 = intent

        // Obtener el correo electrónico del Intent
        val correo = intent1.getStringExtra("correo")

        // Ahora puedes usar el correo electrónico según sea necesario
        Log.d("TAG", "Correo electrónico del usuario: $correo")

        val buttonCalendario : ImageButton = findViewById(R.id.ibCalendario)

        val buttonPreguntasFrecuentes : ImageButton = findViewById(R.id.ibPreguntasFrec)

        val buttonMisMascotas : ImageButton = findViewById(R.id.ibMisMascotas)

        val buttonEntrenamiento : ImageButton = findViewById(R.id.ibEntrenamiento)

        val buttonCerrarSesion : ImageButton = findViewById(R.id.ibCerrarSesion)

        buttonCalendario.setOnClickListener {
            var intent: Intent = Intent( this, CalendarioActivity::class.java)
            intent.putExtra("correo", correo) // Adjunta el correo electrónico como extra al Intent
            startActivity(intent)
        }

        buttonPreguntasFrecuentes.setOnClickListener {
            var intent: Intent = Intent( this, PreguntasFrecActivity::class.java)
            startActivity(intent)
        }

        buttonMisMascotas.setOnClickListener {
            var intent: Intent = Intent( this, MisMascotasActivity::class.java)
            intent.putExtra("correo", correo) // Adjunta el correo electrónico como extra al Intent
            startActivity(intent)
        }

        buttonEntrenamiento.setOnClickListener {
            var intent: Intent = Intent( this, MenuEntrenamientoActivity::class.java)
            startActivity(intent)
        }

        buttonCerrarSesion.setOnClickListener {
            // Cerrar sesión del usuario
            FirebaseAuth.getInstance().signOut()

            // Redirigir a la pantalla de inicio (MainActivity)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Finalizar esta actividad para evitar que el usuario pueda volver atrás con el botón de atrás
            finish()
        }

    }

}