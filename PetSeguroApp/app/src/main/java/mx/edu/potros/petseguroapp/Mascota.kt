package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Mascota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mascota)

        // Obtener los datos de la mascota del Intent
        val intent = intent
        val nombre = intent.getStringExtra("nombre")
        val edad = intent.getIntExtra("edad",0).toString()
        val raza = intent.getStringExtra("raza")
        val cuidadoEsp = intent.getStringExtra("cuidadoEspecial")
        val idDuenio = intent.getStringExtra("idDuenio")
        val correo = intent.getStringExtra("correo")

        // Obtener referencias a los TextView
        val razaText: TextView = findViewById(R.id.etRaza)
        val nombreText: TextView = findViewById(R.id.etNombre)
        val edadText: TextView = findViewById(R.id.etEdad)
        val cuidadoEspecialText: TextView = findViewById(R.id.etCuidado)

        // Establecer los datos de la mascota en los TextView
        nombreText.text = nombre
        edadText.text = edad
        razaText.text = raza
        cuidadoEspecialText.text = cuidadoEsp

        // Obtener referencias a los botones
        val buttonAgendar : Button = findViewById(R.id.btnAgendar)
        val buttonRegresar : Button = findViewById(R.id.btnRegresar)

        // Configurar el botón Regresar para volver a la actividad MisMascotasActivity
        buttonRegresar.setOnClickListener {
            val intent = Intent(this, MisMascotasActivity::class.java)
            intent.putExtra("correo", correo) // Adjunta el correo electrónico como extra al Intent
            startActivity(intent)
        }

        // Configurar el botón Agendar para ir a la actividad AgendarCitaActivity
        buttonAgendar.setOnClickListener {
            val intent = Intent(this, AgendarCitaActivity::class.java)
            intent.putExtra("idDuenio", idDuenio) // Adjunta el id del dueño como extra al Intent
            startActivity(intent)
        }
    }
}
