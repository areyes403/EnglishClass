package mx.edu.itm.link.englishclass.presenter.activities

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import mx.edu.itm.link.englishclass.R
import mx.edu.itm.link.englishclass.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Firebase.initialize(this)
        auth= FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val mail = binding.tfEmailLogin.text.toString().trim()
            val pas = binding.tfPasswordLogin.text.toString()

            if (mail.isBlank()||pas.isBlank()){
                Toast.makeText(this,"Rellena todos los campos",Toast.LENGTH_SHORT).show()
            }else{
                loggin(mail,pas)
            }

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




