package ipvc.estg.olxcm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn : Button = findViewById(R.id.button)
        btn.setOnClickListener{
            val intent = Intent(this, NavDrawer::class.java)
            startActivity(intent)
        }
    }
}