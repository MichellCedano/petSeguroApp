package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RespuestasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_respuestas)

        val buttonRegresar : Button = findViewById(R.id.btnRegresar)


        buttonRegresar.setOnClickListener {
            var intent: Intent = Intent( this, PreguntasFrecActivity::class.java)
            startActivity(intent)
        }
    }
}