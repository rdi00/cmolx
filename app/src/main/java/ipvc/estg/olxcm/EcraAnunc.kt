package ipvc.estg.olxcm

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class EcraAnunc : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecra_anunc)

        var intent = intent
        val tit = intent.getStringExtra("EXTRA_MESSAGE")
        val preco = intent.getStringExtra("PRECO").toString()
        val descricao = intent.getStringExtra("DESCRICAO").toString()
        val localizacao= intent.getStringExtra("LATITIDAO")
        val imagem= intent.getStringExtra("ITUSAO").toString()
        val data= intent.getStringExtra("DATA").toString()
        val user= intent.getStringExtra("USER").toString()




        findViewById<TextView>(R.id.tituloanunc).setText(tit)
        findViewById<TextView>(R.id.descrianuncio).setText("Descrição: " + descricao)
        findViewById<TextView>(R.id.precoAnunc).setText("Preço: " + preco + "euros")
        findViewById<TextView>(R.id.localizacao).setText("Zona de Venda: " + localizacao)
        //latitude e longitude para o mapa???


        //read imagem
        val imageBytes = Base64.getDecoder().decode(imagem)
        val decoded = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        findViewById<ImageView>(R.id.imgAnuncio).setImageBitmap(decoded)
        //favoritos

        findViewById<TextView>(R.id.dataText).setText(data)

        val btnChat = findViewById<Button>(R.id.btnChat)


        btnChat.setOnClickListener{

            val intent = Intent(this, ChatLog::class.java)
            intent.putExtra(USER_KEY, user)
            startActivity(intent)
        }

    }

    companion object {
        val USER_KEY = "USER_KEY"
    }
}