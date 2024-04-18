package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuEntrenamientoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_entrenamiento)

        val buttonRegresar : Button = findViewById(R.id.btnRegresar)

        val butttonEntrenamiento1 : Button = findViewById(R.id.btnSentarse)
        val butttonEntrenamiento2 : Button = findViewById(R.id.btnPonerseADosPatas)
        val butttonEntrenamiento3 : Button = findViewById(R.id.btnDarLaPata)

        buttonRegresar.setOnClickListener {
            var intent: Intent = Intent( this, MenuActivity::class.java)
            startActivity(intent)
        }

        butttonEntrenamiento1.setOnClickListener {
            var intent: Intent = Intent( this, EntrenamientoActivity::class.java)
            startActivity(intent)
        }
        butttonEntrenamiento2.setOnClickListener {
            var intent: Intent = Intent( this, EntrenamientoActivity::class.java)
            startActivity(intent)
        }
        butttonEntrenamiento3.setOnClickListener {
            var intent: Intent = Intent( this, EntrenamientoActivity::class.java)
            startActivity(intent)
        }
    }
}