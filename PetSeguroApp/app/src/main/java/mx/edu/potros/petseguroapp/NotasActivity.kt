package mx.edu.potros.petseguroapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class NotasActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var etNota: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        // Inicialización de Firebase Authentication y Firebase Database
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()

        // Obtener la referencia del EditText
        etNota = findViewById(R.id.etNota)

        // Obtener la referencia del botón Aceptar
        val btnAceptar = findViewById<Button>(R.id.btnAceptar)
        btnAceptar.setOnClickListener {
            // Verificar si el usuario está autenticado
            if (firebaseAuth.currentUser != null) {
                // Guardar la nota en la base de datos utilizando el UID del usuario como identificador
                guardarNota(etNota.text.toString(), firebaseAuth.currentUser!!.uid, firebaseAuth.currentUser!!.email!!)
            } else {
                // Si el usuario no está autenticado, mostrar un mensaje de error
                showToast("Usuario no autenticado. Por favor, inicia sesión.")
            }
        }
    }

    private fun guardarNota(nota: String, userId: String, userEmail: String) {
        // Obtener una referencia a la colección "Notas" para el usuario
        val notasRef = firebaseDatabase.reference.child("Notas").child(userId)

        // Generar una clave única para la nueva nota
        val nuevaNotaKey = notasRef.push().key

        // Verificar si la clave es null
        if (nuevaNotaKey != null) {
            // Guardar la nota utilizando la clave generada
            val notaMap = HashMap<String, Any>()
            notaMap["texto"] = nota
            notaMap["usuario"] = userEmail // Cambia el campo de correo a "usuario"

            // Guardar la nota en la base de datos
            notasRef.child(nuevaNotaKey).setValue(notaMap)
                .addOnSuccessListener {
                    Log.d(TAG, "Nota guardada exitosamente")
                    showToast("Nota guardada exitosamente")
                    finish() // Cerrar la actividad después de guardar la nota
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error al guardar la nota", e)
                    showToast("Error al guardar la nota")
                }
        } else {
            Log.e(TAG, "Error al generar la clave para la nota")
            showToast("Error al generar la clave para la nota")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val TAG = "NotasActivity"
    }
}







