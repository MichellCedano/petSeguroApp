package mx.edu.potros.petseguroapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class NotasActivity : AppCompatActivity() {

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var etNota: EditText
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        // Inicialización de Firebase Database
        firebaseDatabase = FirebaseDatabase.getInstance()

        // Obtener el ID del usuario actual
        userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        // Obtener la referencia del EditText
        etNota = findViewById(R.id.etNota)

        // Obtener la referencia del botón Aceptar
        val btnAceptar = findViewById<Button>(R.id.btnAceptar)
        btnAceptar.setOnClickListener {
            // Guardar la nota en la base de datos
            guardarNota(etNota.text.toString(), userId)

            // Devolver al usuario a la actividad CalendarioActivity
            val intent = Intent(this, CalendarioActivity::class.java)
            startActivity(intent)
        }
    }

    private fun guardarNota(nota: String, userId: String) {
        // Obtener una referencia a la colección "Notas" para el usuario actual
        val notasRef = firebaseDatabase.reference.child("Notas").child(userId)

        // Generar una clave única para la nueva nota
        val nuevaNotaKey = notasRef.push().key

        // Verificar si la clave es null
        if (nuevaNotaKey != null) {
            // Guardar la nota utilizando la clave generada
            val notaMap = HashMap<String, Any>()
            notaMap["texto"] = nota

            // Guardar la nota en la base de datos
            notasRef.child(nuevaNotaKey).setValue(notaMap)
                .addOnSuccessListener {
                    Log.d(TAG, "Nota guardada exitosamente")
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error al guardar la nota", e)
                }
        } else {
            Log.e(TAG, "Error al generar la clave para la nota")
        }
    }

    companion object {
        private const val TAG = "NotasActivity"
    }
}


