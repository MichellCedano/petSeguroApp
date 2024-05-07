package mx.edu.potros.petseguroapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.HashMap

class AgendarCitaActivity : AppCompatActivity() {

lateinit var calendarView: CalendarView
lateinit var serviciosSpinner: Spinner
lateinit var btnListo: Button
    lateinit var tvNombreMascota: TextView

lateinit var database: FirebaseDatabase
lateinit var citasRef: DatabaseReference

var selectedDate: String? = null
var selectedService: String? = null
var idMascota: String? = null

    override fun onCreate(savedBundle: Bundle?) {
        super.onCreate(savedBundle)
        setContentView(R.layout.activity_agendar_cita)

        // Inicializar el TextView para mostrar el nombre de la mascota
        tvNombreMascota = findViewById(R.id.tvNombreMascota)

        // Obtener el nombre de la mascota desde el Intent
        val nombreMascota = intent.getStringExtra("nombreMascota")
        val idMascota = intent.getStringExtra("idMascota")  // Obtener el ID de la mascota

        nombreMascota?.let {
            tvNombreMascota.text = it
        }


// Inicializar el TextView para mostrar el nombre de la mascota
        tvNombreMascota = findViewById(R.id.tvNombreMascota)
        tvNombreMascota.text =
            nombreMascota ?: "Nombre no disponible"  // Mostrar el nombre de la mascota

        // Inicializar vistas
        calendarView = findViewById(R.id.calendarView)
        serviciosSpinner = findViewById(R.id.servicios_spinner)
        btnListo = findViewById(R.id.btnListo)

        // Crear una lista de cadenas para llenar el Spinner
        val servicios = listOf("Baño", "Corte de pelo", "Consulta", "Vacunación", "Desparasitación")

        // Crear un adaptador para el Spinner con un diseño básico
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, servicios)

        // Configurar el diseño para cuando el Spinner está desplegado
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Asignar el adaptador al Spinner
        serviciosSpinner.adapter = adapter

        // Configurar Firebase
        database = FirebaseDatabase.getInstance()
        citasRef = database.getReference("Citas")

        // Configurar CalendarView
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$year-${month + 1}-$dayOfMonth"
        }

        // Configurar Spinner para obtener el tipo de servicio
        serviciosSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedService = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No hacer nada
            }
        }

        // Guardar cita en Firebase cuando se hace clic en el botón "Listo"
        btnListo.setOnClickListener {
            if (selectedDate != null && selectedService != null && idMascota != null) {
                val cita = HashMap<String, Any>()
                cita["fecha"] = selectedDate!!
                cita["servicio"] = selectedService!!
                cita["idMascota"] = idMascota!!  // Relacionar la cita con la mascota

                citasRef.push().setValue(cita)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Cita agendada con éxito.", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            this,
                            "Error al agendar la cita: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } else {
                Toast.makeText(
                    this,
                    "Por favor selecciona una fecha y un servicio.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }}