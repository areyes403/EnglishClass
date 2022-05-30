package mx.edu.itm.link.englishclass.views.activities

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import mx.edu.itm.link.englishclass.R


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Firebase.initialize(this)
        auth= FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            val mail=tfEmailLogin.text.toString().trim()
            val pas=tfPasswordLogin.text.toString()

            if (mail.isBlank()||pas.isBlank()){
                Toast.makeText(this,"Rellena todos los campos",Toast.LENGTH_SHORT).show()
            }else{
                loggin(mail,pas)
            }

        }
        btnNewRegister.setText(Html.fromHtml(getResources().getString(R.string.btnRegister)))

        btnNewRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun loggin(e: String, p: String){

        auth.signInWithEmailAndPassword(e, p).addOnCompleteListener(this){
            println("usuario: "+e+"contraseña:"+p)
            println(it.result)
            if (it.isSuccessful){
                startActivity(Intent(this, LoggedActivity::class.java))
                Toast.makeText(this,"Ingresado",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Usuario o Contraseña Incorrectos",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            startActivity(Intent(this, LoggedActivity::class.java))
        }
    }

}




