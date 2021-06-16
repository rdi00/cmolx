package ipvc.estg.olxcm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import ipvc.estg.olxcm.R

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        
        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener {
            val intent = Intent(this, NavDrawer::class.java)
            startActivity(intent)
        }
    }
}