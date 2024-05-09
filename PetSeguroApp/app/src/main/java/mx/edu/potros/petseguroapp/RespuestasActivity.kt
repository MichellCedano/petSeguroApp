package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class RespuestasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_respuestas)

        val correo = intent.getStringExtra("correo")

        val buttonRegresar: Button = findViewById(R.id.btnRegresar)
        val textViewPregunta: TextView = findViewById(R.id.tvTitulo)
        val textViewRespuesta: TextView = findViewById(R.id.answer)

        // Obtener la pregunta seleccionada del intent
        val pregunta = intent.getStringExtra("pregunta")

        // Mostrar la pregunta en el TextView correspondiente
        textViewPregunta.text = pregunta

        // Mostrar la respuesta correspondiente a la pregunta seleccionada
        val respuesta = obtenerRespuesta(pregunta)
        textViewRespuesta.text = respuesta

        buttonRegresar.setOnClickListener {
            val intent = Intent(this, PreguntasFrecActivity::class.java)
            intent.putExtra("correo", correo) // Adjunta el correo electrónico como extra al Intent
            startActivity(intent)
        }
    }

    // Función para obtener la respuesta correspondiente a la pregunta
    private fun obtenerRespuesta(pregunta: String?): String {
        // Implementa aquí la lógica para obtener la respuesta según la pregunta seleccionada
        // Por ahora, es solo un ejemplo
        return when (pregunta) {
            "1. ¿Cómo puedo mantener a mi perro limpio y saludable?" -> getString(R.string.respuesta_1)
            "2. ¿Qué tipo de juguetes y actividades son mejores para mi perro?" -> getString(R.string.respuesta_2)
            "3. ¿Cuáles son los requisitos de vacunación y desparasitación para mi perro?" -> getString(R.string.respuesta_3)
            else -> "No hay respuesta disponible."
        }
    }
}