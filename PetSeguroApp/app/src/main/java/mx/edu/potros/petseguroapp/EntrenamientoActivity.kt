package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EntrenamientoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenamiento)

        val buttonRegresar : Button = findViewById(R.id.btnRegresar)


        buttonRegresar.setOnClickListener {
            var intent: Intent = Intent( this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}