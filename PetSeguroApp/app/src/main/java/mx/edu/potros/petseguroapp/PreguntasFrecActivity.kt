package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class PreguntasFrecActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preguntas_frec)
        val correo = intent.getStringExtra("correo")

        val question1 = findViewById<TextView>(R.id.question1)
        val question2 = findViewById<TextView>(R.id.question2)
        val question3 = findViewById<TextView>(R.id.question3)
        val buttonRegresar : Button = findViewById(R.id.btnRegresar)

        buttonRegresar.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("correo", correo) // Adjunta el correo electrónico como extra al Intent
            startActivity(intent)
        }

        question1.setOnClickListener {
            val preguntaSeleccionada = question1.text.toString()
            abrirRespuestasActivity(preguntaSeleccionada)
        }

        question2.setOnClickListener {
            val preguntaSeleccionada = question2.text.toString()
            abrirRespuestasActivity(preguntaSeleccionada)
        }

        question3.setOnClickListener {
            val preguntaSeleccionada = question3.text.toString()
            abrirRespuestasActivity(preguntaSeleccionada)
        }
    }

    private fun abrirRespuestasActivity(preguntaSeleccionada: String) {
        val correo = intent.getStringExtra("correo")
        val intent = Intent(this, RespuestasActivity::class.java)
        intent.putExtra("correo", correo) // Adjunta el correo electrónico como extra al Intent
        intent.putExtra("pregunta", preguntaSeleccionada)
        startActivity(intent)
    }
}