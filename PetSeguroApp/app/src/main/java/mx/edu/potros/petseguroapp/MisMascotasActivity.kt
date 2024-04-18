package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MisMascotasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_mascotas)

        val buttonRegistrar : Button = findViewById(R.id.btnRegistroMascota)

        val buttonRegresar : Button = findViewById(R.id.btnRegresar)

        val butttonMascota1 : ImageButton = findViewById(R.id.ibMascota1)
        val butttonMascota2 : ImageButton = findViewById(R.id.ibMascota2)
        val butttonMascota3 : ImageButton = findViewById(R.id.ibMascota3)
        val butttonMascota4 : ImageButton = findViewById(R.id.ibMascota4)

        buttonRegistrar.setOnClickListener {
            var intent: Intent = Intent( this, RegistroMascota::class.java)
            startActivity(intent)
        }

        buttonRegresar.setOnClickListener {
            var intent: Intent = Intent( this, MenuActivity::class.java)
            startActivity(intent)
        }

        butttonMascota1.setOnClickListener {
            var intent: Intent = Intent( this, Mascota::class.java)
            startActivity(intent)
        }
        butttonMascota2.setOnClickListener {
            var intent: Intent = Intent( this, Mascota::class.java)
            startActivity(intent)
        }
        butttonMascota3.setOnClickListener {
            var intent: Intent = Intent( this, Mascota::class.java)
            startActivity(intent)
        }
        butttonMascota4.setOnClickListener {
            var intent: Intent = Intent( this, Mascota::class.java)
            startActivity(intent)
        }
    }
}