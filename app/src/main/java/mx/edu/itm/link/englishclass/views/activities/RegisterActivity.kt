package mx.edu.itm.link.englishclass.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import mx.edu.itm.link.englishclass.Data.User
import mx.edu.itm.link.englishclass.R


class RegisterActivity : AppCompatActivity() {


    private lateinit var database:FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    private lateinit var dbReference:DatabaseReference

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_register)

            database= FirebaseDatabase.getInstance()
            auth= FirebaseAuth.getInstance()
            dbReference=database.reference.child("users")

            //para trabajar con el spinner
           /* binding.spCarrera.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    //Toast.makeText(this@RegisterActivity, "onItemSelected name: $test id: $id", Toast.LENGTH_SHORT).show()
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }*/

            //getUsers()
            btnRegister.setOnClickListener {
                //val toast = Toast.makeText(this, "Registrado Completado", Toast.LENGTH_LONG)
                try {
                    /*
                    val usr=User(
                        tfEmail.text.toString(),
                        tfPassword.text.toString(),
                        tfName.text.toString(),
                        tfSurnames.text.toString(),
                        spCarrera.getItemAtPosition(spCarrera.selectedItemPosition).toString(),
                        false,
                        "defaultIMG")
                     */

                    writeNewuser()
                    //finish()
                    //toast.show()
                }catch (e: ExceptionInInitializerError){
                    print(e)
                }

            }
    }

    fun writeNewuser(){
        val email=tfEmail.text.toString().trim()
        val pas=tfPassword.text.toString()
        val nombre=tfName.text.toString()
        val apellidos=tfSurnames.text.toString()
        val carrera=spCarrera.getItemAtPosition(spCarrera.selectedItemPosition).toString()
        if(email.isBlank() || pas.isBlank()) {
            Toast.makeText(this,"Ingrese los campos",Toast.LENGTH_SHORT).show()
            return
        }else{
            auth.createUserWithEmailAndPassword(email,pas).addOnCompleteListener(this){
                task->
                if(task.isSuccessful){
                    val a: FirebaseUser?=auth.currentUser
                    //verifyEmail(user)
                    val userDB=dbReference.child(a?.uid!!)
                    userDB.child("id").setValue(a.uid!!)
                    userDB.child("correo").setValue(email)
                    userDB.child("pass").setValue(pas)
                    userDB.child("nombre").setValue(nombre)
                    userDB.child("apellidos").setValue(apellidos)
                    userDB.child("carrera").setValue(carrera)
                    userDB.child("status").setValue("true")
                    userDB.child("profileImage").setValue("null")
                    Toast.makeText(this,"Registrado correctamente",Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    task.result
                }
            }
        }
            //database.child("users").child(database.push().key.toString()).setValue(user)
    }


}