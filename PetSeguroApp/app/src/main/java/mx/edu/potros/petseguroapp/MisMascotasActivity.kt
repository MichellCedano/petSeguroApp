package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MisMascotasActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_mascotas)

        val intent = intent
        val correo = intent.getStringExtra("correo")

        database = FirebaseDatabase.getInstance()

        val buttonRegistrar: Button = findViewById(R.id.btnRegistroMascota)
        val buttonRegresar: Button = findViewById(R.id.btnRegresar)

        buttonRegistrar.setOnClickListener {
            val intent = Intent(this, RegistroMascota::class.java)
            intent.putExtra("correo", correo) // Adjunta el correo electrónico como extra al Intent
            startActivity(intent)
        }

        buttonRegresar.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("correo", correo) // Adjunta el correo electrónico como extra al Intent
            startActivity(intent)
        }

        // Asociar los botones directamente a los datos de las mascotas en la base de datos
        val buttonIds = listOf(R.id.ibMascota1, R.id.ibMascota2, R.id.ibMascota3, R.id.ibMascota4)
        for (i in 0 until buttonIds.size) {
            val button = findViewById<ImageButton>(buttonIds[i])
            button.setOnClickListener {
                loadMascota(correo, i + 1) // El índice comienza en 1
            }
        }
    }

    private fun loadMascota(correo: String?, numMascota: Int) {
        correo?.let { userEmail ->
            val userRef = database.getReference("Users").orderByChild("correo").equalTo(userEmail)
            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val userId = dataSnapshot.children.firstOrNull()?.key
                        userId?.let { uid ->
                            val mascotaRef = database.getReference("Mascotas").orderByChild("idDuenio").equalTo(uid)
                            mascotaRef.addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(mascotaSnapshot: DataSnapshot) {
                                    val mascotas = mutableListOf<Pair<String, Mascot?>>()
                                    mascotaSnapshot.children.forEach { mascotaSnapshot ->
                                        mascotas.add(
                                            (mascotaSnapshot.key ?: "") to mascotaSnapshot.getValue(
                                                Mascot::class.java
                                            )
                                        )
                                    }
                                    // Verificar si hay una mascota asociada al número del botón
                                    // Dentro de MisMascotasActivity
                                    if (numMascota <= mascotas.size) {
                                        val (mascotaId, mascota) = mascotas[numMascota - 1]  // El índice comienza en 0
                                        mascota?.let {
                                            // Abre MascotaActivity con los datos de la mascota
                                            val intent = Intent(
                                                this@MisMascotasActivity,
                                                Mascota::class.java
                                            )
                                            intent.putExtra("raza", mascota.raza)
                                            intent.putExtra("nombre", mascota.nombre)
                                            intent.putExtra("edad", mascota.edad)
                                            intent.putExtra(
                                                "cuidadoEspecial",
                                                mascota.cuidadoEspecial
                                            )
                                            intent.putExtra(
                                                "idMascota",
                                                mascotaId
                                            )  // ID de la mascota
                                            intent.putExtra(
                                                "correo",
                                                correo
                                            )  // Adjunta el correo electrónico como extra al Intent
                                            startActivity(intent)
                                        }
                                    } else {
                                        Toast.makeText(
                                            this@MisMascotasActivity,
                                            "No hay mascota asociada a este botón",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    /**
                                    if (numMascota <= mascotas.size) {
                                    val (mascotaId, mascota) = mascotas[numMascota - 1]
                                    mascota?.let {
                                    val intent = Intent(this@MisMascotasActivity, AgendarCitaActivity::class.java)
                                    intent.putExtra("idMascota", mascotaId)  // ID de la mascota
                                    intent.putExtra("nombreMascota", mascota.nombre)  // Nombre de la mascota
                                    startActivity(intent)  // Inicia AgendarCitaActivity
                                    }
                                    } else {
                                    Toast.makeText(this@MisMascotasActivity, "No hay mascota asociada", Toast.LENGTH_SHORT).show()
                                    }

                                     */
                                }

                                override fun onCancelled(error: DatabaseError) {
                                    Log.e("MisMascotasActivity", "Error de base de datos: ${error.message}")
                                }
                            })
                        }
                    } else {
                        Toast.makeText(this@MisMascotasActivity, "No se encontró ningún usuario con el correo proporcionado", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("MisMascotasActivity", "Error de base de datos: ${error.message}")
                }
            })
        }
    }

}