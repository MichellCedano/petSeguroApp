package mx.edu.potros.petseguroapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.util.*

class CalendarioActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var calendarView: CalendarView
    private lateinit var tvNota: TextView
    private lateinit var citasDelUsuario: List<Cita>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendario)
        val intent = intent
        // Inicialización de FirebaseDatabase
        database = FirebaseDatabase.getInstance()

        // Obtención del CalendarView del layout
        calendarView = findViewById(R.id.calendarView)

        // Obtén el correo electrónico del usuario actual
        val correo = intent.getStringExtra("correo")

        // Obtención de TextView para mostrar las notas
        tvNota = findViewById(R.id.tvNota)

        // Cargar las notas al iniciar la actividad
        cargarNotas(correo)

        // Consultar las citas del usuario y marcarlas en el calendario
        correo?.let { correo ->
            consultarCitasUsuario(correo) { dataSnapshotList ->
                // Convertir los DataSnapshot a lista de citas
                citasDelUsuario = dataSnapshotList.mapNotNull { dataSnapshot ->
                    val fecha = dataSnapshot.child("fecha").getValue(String::class.java)
                    val nombreMascota = dataSnapshot.child("nombreMascota").getValue(String::class.java)
                    val servicio = dataSnapshot.child("servicio").getValue(String::class.java)
                    fecha?.let {
                        Cita(it, nombreMascota ?: "", servicio ?: "")
                    }
                }
                // Llamada a la función para marcar las fechas en el calendario
                marcarFechasEnCalendario(citasDelUsuario)
            }
        }

        // Listener para manejar el cambio de fecha en el CalendarView
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val fechaSeleccionada = "$year-${month + 1}-$dayOfMonth" // Obtener la fecha seleccionada
            // Verificar si hay una cita para la fecha seleccionada
            val citaParaFechaSeleccionada = citasDelUsuario.find { it.fecha == fechaSeleccionada }
            if (citaParaFechaSeleccionada != null) {
                // Si hay una cita para la fecha seleccionada, mostrar detalles
                mostrarDetallesCita(citaParaFechaSeleccionada)
            } else {
                // Si no hay una cita para la fecha seleccionada, mostrar mensaje de no hay citas
                mostrarDialogoSinCitas(fechaSeleccionada)
            }
        }

        // Obtención del botón de regresar
        val btnRegresar = findViewById<Button>(R.id.btnRegresar)
        btnRegresar.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("correo", correo) // Adjunta el correo electrónico como extra al Intent
            startActivity(intent)
        }

        // Listener para el TextView de las notas
        tvNota.setOnClickListener {
            val intent = Intent(this, NotasActivity::class.java)
            intent.putExtra("correo", correo) // Adjunta el correo electrónico como extra al Intent
            startActivity(intent)
        }
    }

    private fun marcarFechasEnCalendario(citas: List<Cita>) {
        for (cita in citas) {
            val parts = cita.fecha.split("-")
            val year = parts[0].toInt()
            val month = parts[1].toInt() - 1 // El mes en Calendar es 0-indexado
            val dayOfMonth = parts[2].toInt()
            // Marcar la fecha en el calendario
            calendarView.setDate(Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }.timeInMillis, true, true)
        }
    }

    private fun consultarCitasUsuario(correo: String, callback: (List<DataSnapshot>) -> Unit) {
        val citasUsuario = mutableListOf<DataSnapshot>()

        val userRef = database.getReference("Users").orderByChild("correo").equalTo(correo)
        userRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(userSnapshot: DataSnapshot) {
                if (userSnapshot.exists()) {
                    val userId = userSnapshot.children.firstOrNull()?.key
                    userId?.let { userId ->
                        val mascotasRef = database.getReference("Mascotas").orderByChild("idDuenio").equalTo(userId)
                        mascotasRef.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(mascotasSnapshot: DataSnapshot) {
                                for (mascotaSnapshot in mascotasSnapshot.children) {
                                    val idMascota = mascotaSnapshot.key
                                    idMascota?.let {
                                        val citasRef = database.getReference("Citas").orderByChild("idMascota").equalTo(idMascota)
                                        citasRef.addListenerForSingleValueEvent(object : ValueEventListener {
                                            override fun onDataChange(citasSnapshot: DataSnapshot) {
                                                for (citaSnapshot in citasSnapshot.children) {
                                                    citasUsuario.add(citaSnapshot)
                                                }
                                                callback(citasUsuario)
                                            }

                                            override fun onCancelled(databaseError: DatabaseError) {
                                                Log.e(TAG, "Error obteniendo citas: ", databaseError.toException())
                                            }
                                        })
                                    }
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                Log.e(TAG, "Error obteniendo mascotas del usuario: ", databaseError.toException())
                            }
                        })
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e(TAG, "Error obteniendo usuario: ", databaseError.toException())
            }
        })
    }

    private fun mostrarDetallesCita(cita: Cita) {
        // Mostrar detalles de la cita en un mensaje emergente
        val detallesCita = "Nombre de la mascota: ${cita.nombreMascota}\n" +
                "Servicio: ${cita.servicio}\n"
        mostrarDialogoCita(cita.fecha, detallesCita)
    }

    private fun cargarNotas(correo: String?) {
        correo?.let { correo ->
            val notasRef = database.getReference("Notas").orderByChild("usuario").equalTo(correo)
            notasRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val notasStringBuilder = StringBuilder()
                    dataSnapshot.children.forEach { notaSnapshot ->
                        val nota = notaSnapshot.child("texto").getValue(String::class.java)
                        nota?.let {
                            notasStringBuilder.append(it).append("\n") // Agregar la nota y un salto de línea
                        }
                    }
                    tvNota.text = notasStringBuilder.toString()
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(TAG, "Error al obtener notas: ", databaseError.toException())
                }
            })
        }
    }



    companion object {
        const val TAG = "CalendarioActivity"
    }

    private fun mostrarDialogoCita(fecha: String, detallesCita: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Detalles de la cita agendada para el $fecha")
        builder.setMessage(detallesCita)
        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun mostrarDialogoSinCitas(fecha: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No hay citas agendadas para el $fecha")
        builder.setMessage("No hay citas programadas para esta fecha.")
        builder.setPositiveButton("Aceptar") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    data class Cita(val fecha: String, val nombreMascota: String, val servicio: String)

}





