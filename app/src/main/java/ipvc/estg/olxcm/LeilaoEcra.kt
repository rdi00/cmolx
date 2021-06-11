package ipvc.estg.olxcm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import ipvc.estg.olxcm.api.Endpoints
import ipvc.estg.olxcm.api.Leilao
import ipvc.estg.olxcm.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeilaoEcra : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leilao_ecra)


        var intent = intent
        val tit = intent.getStringExtra("TIT")
        val id = intent.getIntExtra("ID",0)
        val data = intent.getStringExtra("DATA")
        val preco = intent.getStringExtra("PRECO")
        val lance = intent.getStringExtra("LANCE")
        val img = intent.getStringExtra("IMG")
        val vendedor = intent.getStringExtra("VENDEDOR")
        val comprador = intent.getStringExtra("COMPRADOR").toString()


        findViewById<TextView>(R.id.titLeilao).setText(tit)
        findViewById<TextView>(R.id.precoInicial).setText(preco)
        findViewById<TextView>(R.id.ultimoLance).setText(lance)
        findViewById<TextView>(R.id.dataFim).setText(data)

        //imagem
        //read imagem
        //val imageBytes = Base64.getDecoder().decode()
        // val decoded = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        //button licitar

        val licita = findViewById<Button>(R.id.licitar)
        licita.setOnClickListener {
            val novoLicita = findViewById<EditText>(R.id.novoValor).text.toString()
            val request = ServiceBuilder.buildService(Endpoints::class.java)
            val call = request.lance(id, comprador , novoLicita )
            var intento = Intent(this, LeilaoEcra::class.java)


            //edita
            call.enqueue(object : Callback<Leilao> {
                override fun onResponse(call: Call<Leilao>, response: Response<Leilao>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@LeilaoEcra, "Lance Feito", Toast.LENGTH_SHORT).show()
                        startActivity(intento)

                    } else {
                        Toast.makeText(this@LeilaoEcra,"Erro no lance", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Leilao>, t: Throwable) {
                    Toast.makeText(this@LeilaoEcra, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
            
        }

        //atualiza ecra

        fun atualiza(){

        }

    }


}