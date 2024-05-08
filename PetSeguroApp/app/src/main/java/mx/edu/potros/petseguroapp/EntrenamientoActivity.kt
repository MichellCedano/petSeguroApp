package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class EntrenamientoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrenamiento)

        val tvTitulo = findViewById<TextView>(R.id.tvTituloEntrenamiento)

        val buttonRegresar: Button = findViewById(R.id.btnRegresar)
        buttonRegresar.setOnClickListener {
            finish()
        }

        // Recibir el nombre del entrenamiento seleccionado del intent
        val entrenamiento = intent.getStringExtra("entrenamiento")

        // Configurar la actividad para mostrar el entrenamiento correspondiente
        when (entrenamiento) {
            "Sentarse" -> {
                tvTitulo.text = "Sentarse"
                val imageView = findViewById<ImageView>(R.id.imageView)
                val tvInstrucciones = findViewById<TextView>(R.id.tvInstrucciones)

                imageView.setImageResource(R.drawable.entrenamiento_sentado)
                tvInstrucciones.text = getString(R.string.entrenamiento_1)
            }
            "Ponerse a dos patas" -> {
                tvTitulo.text = "Ponerse a dos patas"
                val imageView = findViewById<ImageView>(R.id.imageView)
                val tvInstrucciones = findViewById<TextView>(R.id.tvInstrucciones)

                imageView.setImageResource(R.drawable.entrenamiento_dos_patas)
                tvInstrucciones.text = getString(R.string.entrenamiento_2)
            }
            "Dar la pata" -> {
                tvTitulo.text = "Dar la pata"
                val imageView = findViewById<ImageView>(R.id.imageView)
                val tvInstrucciones = findViewById<TextView>(R.id.tvInstrucciones)

                imageView.setImageResource(R.drawable.entrenamiento_pata)
                tvInstrucciones.text = getString(R.string.entrenamiento_3)
            }

        }
    }
}