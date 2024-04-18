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

        val question1 = findViewById<TextView>(R.id.question1)
        val question2 = findViewById<TextView>(R.id.question2)
        val question3 = findViewById<TextView>(R.id.question3)
        val buttonRegresar : Button = findViewById(R.id.btnRegresar)


        buttonRegresar.setOnClickListener {
            var intent: Intent = Intent( this, MenuActivity::class.java)
            startActivity(intent)
        }

        question1.setOnClickListener {
            var intent: Intent = Intent( this, RespuestasActivity::class.java)
            startActivity(intent)

        }

        question2.setOnClickListener {
            var intent: Intent = Intent( this, RespuestasActivity::class.java)
            startActivity(intent)

        }

        question3.setOnClickListener {
            var intent: Intent = Intent( this, RespuestasActivity::class.java)
            startActivity(intent)

        }
    }

}