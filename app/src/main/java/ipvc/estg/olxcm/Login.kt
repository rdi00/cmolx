package ipvc.estg.olxcm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import ipvc.estg.olxcm.R
import ipvc.estg.olxcm.api.Endpoints
import ipvc.estg.olxcm.api.ServiceBuilder
import ipvc.estg.olxcm.api.Utilizador
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharedPref: SharedPreferences = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val id:Int = sharedPref.getInt(R.string.id.toString(), 0)
        if(id != 0){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


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

                        if(i.id != 0){


                            val sharedPref: SharedPreferences = getSharedPreferences(
                                getString(R.string.preference_file_key), Context.MODE_PRIVATE
                            )
                            with(sharedPref.edit()) {
                                putInt(R.string.id.toString(), i.id)
                                commit()
                            }
                            Toast.makeText(this@login, "Bem vindo", Toast.LENGTH_SHORT).show()
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this@login, "Erro de login", Toast.LENGTH_SHORT).show()
                        }




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
    }
