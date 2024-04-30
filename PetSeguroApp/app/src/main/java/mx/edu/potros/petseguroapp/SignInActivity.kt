package mx.edu.potros.petseguroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.edu.potros.petseguroapp.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnIniciarSesion.setOnClickListener{
            val mEmail = binding.etEmail.text.toString()
            val mPassword = binding.etcontrasenia.text.toString()

            if (mEmail.isEmpty() || mPassword.isEmpty()) {
                Toast.makeText(baseContext,"ERROR: Mail o contraseña incorrecta", Toast.LENGTH_SHORT).show()
            } else {
                // Deshabilitar el botón mientras se procesa la solicitud de inicio de sesión
                binding.btnIniciarSesion.isEnabled = false
                SignIn(mEmail, mPassword)
            }
        }


        val buttonRegistro : Button = findViewById(R.id.btnRegistrarme)

        buttonRegistro.setOnClickListener {
            var intent: Intent = Intent( this, SignUpActivity::class.java)
            startActivity(intent)
        }



    }

    private fun SignIn(email:String, password:String) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                binding.btnIniciarSesion.isEnabled = true // Habilitar el botón nuevamente
                if(task.isSuccessful) {
                    Log.d("TAG", "Inicio de sesión exitoso!")
                    Toast.makeText(baseContext,"Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show()
                    inicio()
                } else {
                    Log.w("TAG", "Inicio de sesión fallido", task.exception)
                    Toast.makeText(baseContext,"ERROR: Autenticación fallida!", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun inicio(){
        val buttonIniciarSesion : Button = findViewById(R.id.btnIniciarSesion)

        buttonIniciarSesion.setOnClickListener {
            var intent: Intent = Intent( this, MenuActivity::class.java)
            startActivity(intent)
        }
    }

}