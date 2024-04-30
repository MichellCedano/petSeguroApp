package mx.edu.potros.petseguroapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import mx.edu.potros.petseguroapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    private val userRef = FirebaseDatabase.getInstance().getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val dContrasenia: EditText = findViewById<EditText>(R.id.etcontrasenia) as EditText
        val dRepCont: EditText = findViewById<EditText>(R.id.etrepCont) as EditText

        binding.btnregistrarse.setOnClickListener {
            val mUsuario = binding.etUsuario.text.toString()
            val mEmail = binding.etcorreo.text.toString()
            val mPassword = binding.etcontrasenia.text.toString()
            val mPassword2 = binding.etrepCont.text.toString()

            if (mPassword == mPassword2) {
                signUp(mEmail, mPassword)
                saveMarkFromForm()
            } else {
                Toast.makeText(baseContext, "Contraseñas diferentes", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signUp(email: String, password: String) {
        if (password.length < 6) {
            Toast.makeText(baseContext, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "Registro exitoso")
                    inicio()
                } else {
                    Log.w("TAG", "Error al registrarse", task.exception)
                    Toast.makeText(baseContext, "Autenticación fallida: ${task.exception?.message}",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun inicio() {
        val intent: Intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun saveMarkFromForm() {
        val dUsuario: EditText = findViewById<EditText>(R.id.etUsuario) as EditText
        val dCorreo: EditText = findViewById<EditText>(R.id.etcorreo) as EditText
        val dContrasenia: EditText = findViewById<EditText>(R.id.etcontrasenia) as EditText
        val mPassword = dContrasenia.text.toString()

        if (mPassword.length < 6) {
            Toast.makeText(baseContext, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return
        }

        val usuario = User(
            dUsuario.text.toString(),
            dCorreo.text.toString(),
            dContrasenia.text.toString()
        )
        userRef.push().setValue(usuario)
    }
}