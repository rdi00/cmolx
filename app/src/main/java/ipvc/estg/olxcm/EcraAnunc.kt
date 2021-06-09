package ipvc.estg.olxcm

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.*

class EcraAnunc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecra_anunc)

        var intent = intent
        val tit = intent.getStringExtra("EXTRA_MESSAGE")
        val preco = intent.getStringExtra("PRECO")
        val descricao = intent.getStringExtra("DESCRICAO")
        val localizacao= intent.getStringExtra("LATITIDAO")
        val imagem= intent.getStringExtra("ITUSAO")
        val data= intent.getStringExtra("DATA")
        val user= intent.getStringExtra("USER")


        //read imagem
        //val imageBytes = Base64.getDecoder().decode()
       // val decoded = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        findViewById<TextView>(R.id.tituloanunc).setText(tit)
        findViewById<TextView>(R.id.descrianuncio).setText(descricao)
        findViewById<TextView>(R.id.precoAnunc).setText(preco)
        findViewById<TextView>(R.id.localizacao).setText(localizacao)
        //latitude e longitude para o mapa???

        //imagem decode e tal

        //favoritos

        findViewById<TextView>(R.id.dataText).setText(data)

        val btnChat = findViewById<Button>(R.id.btnChat)

        btnChat.setOnClickListener{
            //atividade chat
        }

    }
}