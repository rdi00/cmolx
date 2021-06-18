package ipvc.estg.olxcm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ipvc.estg.olxcm.api.Endpoints
import ipvc.estg.olxcm.api.ServiceBuilder
import ipvc.estg.olxcm.api.Utilizador
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)




        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener {
            val intent = Intent(this, NavDrawer::class.java)
            val username = findViewById<EditText>(R.id.getuder)
            val password = findViewById<EditText>(R.id.getpass)


            var user = username.text.toString()
            var pass =password.text.toString()

            val request = ServiceBuilder.buildService(Endpoints::class.java)
            val call = request.login(user, pass)


            call.enqueue(object : Callback<Utilizador> {
                override fun onResponse(call: Call<Utilizador>, response: Response<Utilizador>) {

                    if (response.isSuccessful) {
                        var i = response.body()!!


                            var email = ""

                            when (user) {
                                "diogo" -> email = "xavier@email.pt"
                                "rafael" -> email = "rafa@email.pt"
                                "jose" -> email = "jose@email.pt"
                                else -> email = "pedro@gmail.pt"
                            }
                            auth = FirebaseAuth.getInstance()
                            auth.signInWithEmailAndPassword(email,"123456")
                            Log.d("testar",auth.currentUser?.uid.toString())
                            Toast.makeText(this@login, "Bem vindo", Toast.LENGTH_SHORT).show()
                            startActivity(intent)
                            finish()



                    }else{
                        Toast.makeText(this@login, "Erro de login", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Utilizador>, t: Throwable) {
                    Toast.makeText(this@login, "Erro de login", Toast.LENGTH_SHORT).show()
                }
            })


        }
    }

    private fun reload() {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}