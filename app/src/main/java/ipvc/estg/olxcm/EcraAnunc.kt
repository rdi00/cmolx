package ipvc.estg.olxcm

import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import ipvc.estg.olxcm.Entitie.anunciofav
import ipvc.estg.olxcm.ViewModel.ViewModel
import java.util.*

class EcraAnunc : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    private lateinit var viewModel: ViewModel
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
        findViewById<TextView>(R.id.precoAnunc).setText("Preço: "+preco)
        findViewById<TextView>(R.id.localizacao).setText("Local de venda: "+localizacao)

        //latitude e longitude para o mapa???


        //read imagem
        val imageBytes = Base64.getDecoder().decode(imagem)
        val decoded = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        findViewById<ImageView>(R.id.imgAnuncio).setImageBitmap(decoded)


        findViewById<TextView>(R.id.dataText).setText(data)

        val btnChat = findViewById<Button>(R.id.btnChat)

        btnChat.setOnClickListener{
            //atividade chat
        }

        //favoritos
        val  checkBox = findViewById<CheckBox>(R.id.checkFav);
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val anuncio = anunciofav(titulo = tit, descricao = descricao, localizacao = localizacao, preco = preco , data = data, utilizador = user)
                viewModel.insert(anuncio)
            }
        }

    }

}