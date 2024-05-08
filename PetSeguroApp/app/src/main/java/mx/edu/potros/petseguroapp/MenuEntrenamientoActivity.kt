package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuEntrenamientoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_entrenamiento)

        val buttonRegresar: Button = findViewById(R.id.btnRegresar)
        val buttonSentarse: Button = findViewById(R.id.btnSentarse)
        val buttonPonerseADosPatas: Button = findViewById(R.id.btnPonerseADosPatas)
        val buttonDarLaPata: Button = findViewById(R.id.btnDarLaPata)

        buttonRegresar.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        buttonSentarse.setOnClickListener {
            abrirEntrenamientoActivity("Sentarse")
        }

        buttonPonerseADosPatas.setOnClickListener {
            abrirEntrenamientoActivity("Ponerse a dos patas")
        }

        buttonDarLaPata.setOnClickListener {
            abrirEntrenamientoActivity("Dar la pata")
        }
    }

    private fun abrirEntrenamientoActivity(entrenamiento: String) {
        val intent = Intent(this, EntrenamientoActivity::class.java)
        intent.putExtra("entrenamiento", entrenamiento)
        startActivity(intent)
    }
}