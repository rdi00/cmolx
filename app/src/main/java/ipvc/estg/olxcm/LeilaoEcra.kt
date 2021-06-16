package ipvc.estg.olxcm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.*
import androidx.annotation.RequiresApi
import ipvc.estg.olxcm.api.Endpoints
import ipvc.estg.olxcm.api.Leilao
import ipvc.estg.olxcm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LeilaoEcra : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leilao_ecra)


        var intent = intent
        val tit = intent.getStringExtra("TIT")
        val idL = intent.getIntExtra("ID",0)
        val data = intent.getStringExtra("DATA")
        val preco = intent.getStringExtra("PRECO")
        val lance = intent.getStringExtra("LANCE")
        val img = intent.getStringExtra("IMG")
        val vendedor = intent.getStringExtra("VENDEDOR")
        val comprador = intent.getIntExtra("COMPRADOR",0)


        findViewById<TextView>(R.id.titLeilao).setText(tit)
        findViewById<TextView>(R.id.precoInicial).setText("Preço inicial:" + preco + "euros")
        findViewById<TextView>(R.id.ultimoLance).setText("Ultimo Lance: " + lance)
        findViewById<TextView>(R.id.dataFim).setText("Data do Fim :" + data)

        val imageBytes = Base64.getDecoder().decode(img)
        val decoded = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        val imgV =findViewById<ImageView>(R.id.imageView2)
        imgV.setImageBitmap(decoded)

        //button licitar

        val licita = findViewById<Button>(R.id.licitar)
        licita.setOnClickListener {
            val sharedPref: SharedPreferences = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE)

            val iduser:Int = sharedPref.getInt(R.string.id.toString(), 0)
            val novoLicita = findViewById<EditText>(R.id.novoValor).text.toString()
            val request = ServiceBuilder.buildService(Endpoints::class.java)
            val call = request.lance(idL, iduser , novoLicita )
            var intento = Intent(this, LeilaoEcra::class.java)


            //edita
            call.enqueue(object : Callback<Leilao> {
                override fun onResponse(call: Call<Leilao>, response: Response<Leilao>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@LeilaoEcra, "Lance Feito", Toast.LENGTH_SHORT).show()
                        //val lei= response.body()!!
                        //findViewById<TextView>(R.id.ultimoLance).setText("Ultimo Lance: " + lei.valor_atual)


                    } else {
                        Toast.makeText(this@LeilaoEcra,"Erro no lance", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Leilao>, t: Throwable) {
                    Toast.makeText(this@LeilaoEcra, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
            
        }

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                val request = ServiceBuilder.buildService(Endpoints::class.java)
                val call = request.leilao(idL)

                //edita
                call.enqueue(object : Callback<Leilao> {
                    override fun onResponse(call: Call<Leilao>, response: Response<Leilao>) {
                        Log.d("OLA", response.toString())
                        if (response.isSuccessful) {
                            //Toast.makeText(this@LeilaoEcra, "reload", Toast.LENGTH_SHORT).show()
                            val l: Leilao = response.body()!!
                            findViewById<TextView>(R.id.ultimoLance).setText("Ultimo Lance: " + l.valor_atual)

                        } else {
                            Toast.makeText(this@LeilaoEcra,"Erro reload", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Leilao>, t: Throwable) {

                        Toast.makeText(this@LeilaoEcra, "${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
                handler.postDelayed(this, 5000)//5 sec delay
            }
        }, 0)

    }


}