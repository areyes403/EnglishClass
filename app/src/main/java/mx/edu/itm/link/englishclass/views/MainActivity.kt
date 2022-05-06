package mx.edu.itm.link.englishclass.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import mx.edu.itm.link.englishclass.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        val myDB= FirebaseDatabase.getInstance()
        database=myDB.reference

        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root

        setContentView(view)


        binding.btnLogin.setOnClickListener {

        }

        binding.btnNewRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))

        }


    }

    fun getUser(userId:String){
/*
        var a=false
        database.child("usuarios").child(userId).get().addOnSuccessListener { user->
            val json= JSONObject(user.value.toString())
            Log.d("ValorFirebase","${user.value}")
            Log.d("ValorJSON","Nombre: ${json.getString("nombre")}  Correo: ${json.getString("correo")}")
            a=true
        }
       return a*/




    }




}