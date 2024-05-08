package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class Mascota : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mascota)

        // Obtener los datos de la mascota del Intent
        val intent = intent
        val nombre = intent.getStringExtra("nombre")
        val edad = intent.getIntExtra("edad", 0).toString()
        val raza = intent.getStringExtra("raza")
        val cuidadoEsp = intent.getStringExtra("cuidadoEspecial")
        val idDuenio = intent.getStringExtra("idDuenio")
        val correo = intent.getStringExtra("correo")
        val idMascota = intent.getStringExtra("idMascota")


        // Obtener referencias a los EditText
        val razaText: EditText = findViewById(R.id.etRaza)
        val nombreText: EditText = findViewById(R.id.etNombre)
        val edadText: EditText = findViewById(R.id.etEdad)
        val cuidadoEspecialText: EditText = findViewById(R.id.etCuidado)

        // Establecer los datos de la mascota en los EditText
        nombreText.setText(nombre)
        edadText.setText(edad)
        razaText.setText(raza)
        cuidadoEspecialText.setText(cuidadoEsp)

        // Obtener referencias a los botones
        val buttonAgendar : Button = findViewById(R.id.btnAgendar)
        val buttonRegresar : Button = findViewById(R.id.btnRegresar)
        val buttonEditar : Button = findViewById(R.id.btnEditar)
        val buttonEliminar : Button = findViewById(R.id.btnEliminar)

        // Configurar el botón Regresar para volver a la actividad MisMascotasActivity
        buttonRegresar.setOnClickListener {
            val intent = Intent(this, MisMascotasActivity::class.java)
            intent.putExtra("correo", correo) // Adjunta el correo electrónico como extra al Intent
            startActivity(intent)
        }

        // Configurar el botón Agendar para ir a la actividad AgendarCitaActivity
        // Configura el botón para agendar cita
        val buttonAgendarCita: Button = findViewById(R.id.btnAgendar)  // Asegúrate de tener el ID correcto
        buttonAgendarCita.setOnClickListener {
            val intent = Intent(this, AgendarCitaActivity::class.java)
            intent.putExtra("idMascota", idMascota)  // ID de la mascota
            intent.putExtra("nombre", nombre)  // Nombre de la mascota
            intent.putExtra("edad", edad)  // Nombre de la mascota
            intent.putExtra("raza", raza)  // Nombre de la mascota
            intent.putExtra("idDuenio", idDuenio)  // Nombre de la mascota
            intent.putExtra("cuidadoEspecial", cuidadoEsp)  // Nombre de la mascota
            intent.putExtra("correo", correo) // Adjunta el correo electrónico como extra al Intent
            startActivity(intent)  // Inicia AgendarCitaActivity
        }

        buttonEditar.setOnClickListener {
            val idMascota = intent.getStringExtra("idMascota") ?: return@setOnClickListener

            // Obtener una referencia a la ubicación de la mascota en la base de datos
            val mascotaRef = FirebaseDatabase.getInstance().getReference("Mascotas").child(idMascota)

            // Actualizar los datos de la mascota
            val nuevaMascota = Mascot(
                razaText.text.toString(),
                nombreText.text.toString(),
                edadText.text.toString().toInt(),
                cuidadoEspecialText.text.toString(),
                idDuenio
            )
            mascotaRef.updateChildren(nuevaMascota.toMap())
                .addOnSuccessListener {
                    Toast.makeText(this, "Mascota actualizada correctamente", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.e("Mascota", "Error al actualizar la mascota: ${e.message}")
                    Toast.makeText(this, "Error al actualizar la mascota", Toast.LENGTH_SHORT).show()
                }
        }



        buttonEliminar.setOnClickListener {
            val idMascota = intent.getStringExtra("idMascota") ?: return@setOnClickListener

            // Obtener una referencia a la ubicación de la mascota en la base de datos
            val mascotaRef = FirebaseDatabase.getInstance().getReference("Mascotas").child(idMascota)

            // Eliminar la mascota de la base de datos
            mascotaRef.removeValue()
                .addOnSuccessListener {
                    Toast.makeText(this, "Mascota eliminada correctamente", Toast.LENGTH_SHORT).show()
                    // Aquí puedes redirigir a la actividad MisMascotasActivity o realizar otra acción necesaria
                }
                .addOnFailureListener { e ->
                    Log.e("Mascota", "Error al eliminar la mascota: ${e.message}")
                    Toast.makeText(this, "Error al eliminar la mascota", Toast.LENGTH_SHORT).show()
                }
        }

    }
}
