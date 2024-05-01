package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RegistroMascota : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var correoUsuario: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_mascota)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        correoUsuario = intent.getStringExtra("correo") ?: ""

        val buttonRegistrar: Button = findViewById(R.id.btnRegistrar)
        val buttonRegresar: Button = findViewById(R.id.btnRegresar)

        buttonRegistrar.setOnClickListener {
            registrarMascota()
        }

        buttonRegresar.setOnClickListener {
            val intent = Intent(this, MisMascotasActivity::class.java)
            intent.putExtra("correo", correoUsuario) // Adjunta el correo electrónico como extra al Intent
            startActivity(intent)
        }
    }

    private fun registrarMascota() {
        val razaText: EditText = findViewById(R.id.etRaza)
        val nombreText: EditText = findViewById(R.id.etNombre)
        val edadText: EditText = findViewById(R.id.etEdad)
        val cuidadoEspecialText: EditText = findViewById(R.id.etCuidado)

        val raza = razaText.text.toString()
        val nombre = nombreText.text.toString()
        val edad = edadText.text.toString().toIntOrNull() // Convertir a Int, manejar el caso de valor nulo o no numérico
        val cuidadoEspecial = cuidadoEspecialText.text.toString()

        if (edad == null) {
            Toast.makeText(this, "Por favor, ingrese una edad válida", Toast.LENGTH_SHORT).show()
            return
        }

        // Buscar al usuario en la base de datos usando el correo electrónico
        val userRef = database.getReference("Users").orderByChild("correo").equalTo(correoUsuario)
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Obtener el ID del primer usuario encontrado
                    val usuarioSnapshot = dataSnapshot.children.firstOrNull()
                    val idUsuario = usuarioSnapshot?.key

                    if (idUsuario != null) {
                        // Creamos una referencia para guardar la mascota en la base de datos
                        val mascotaRef = database.getReference("Mascotas").push()

                        // Creamos el objeto de la mascota
                        val nuevaMascota = Mascot(raza, nombre, edad, cuidadoEspecial, idUsuario)

                        // Guardamos la mascota en la base de datos
                        mascotaRef.setValue(nuevaMascota)
                            .addOnSuccessListener {
                                Toast.makeText(this@RegistroMascota, "Mascota registrada exitosamente", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { e ->
                                Log.e("RegistroMascota", "Error al registrar la mascota: ${e.message}")
                                Toast.makeText(this@RegistroMascota, "Error al registrar la mascota", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Log.e("RegistroMascota", "No se encontró un usuario con el correo proporcionado")
                        Toast.makeText(this@RegistroMascota, "Error: No se encontró un usuario con el correo proporcionado", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Log.e("RegistroMascota", "No se encontró ningún usuario con el correo proporcionado")
                    Toast.makeText(this@RegistroMascota, "Error: No se encontró ningún usuario con el correo proporcionado", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("RegistroMascota", "Error de base de datos: ${error.message}")
                Toast.makeText(this@RegistroMascota, "Error de base de datos", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
