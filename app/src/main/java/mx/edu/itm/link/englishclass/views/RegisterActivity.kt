package mx.edu.itm.link.englishclass.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import mx.edu.itm.link.englishclass.Data.User
import mx.edu.itm.link.englishclass.databinding.ActivityRegisterBinding
import org.json.JSONObject
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var database:DatabaseReference

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding= ActivityRegisterBinding.inflate(layoutInflater)
            val view=binding.root
            setContentView(view)

            val myDB=FirebaseDatabase.getInstance()
            database=myDB.reference

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
            binding.btnRegister.setOnClickListener {
                //val test = binding.spCarrera.getItemAtPosition(binding.spCarrera.selectedItemPosition).toString()
                val toast = Toast.makeText(this, "Ajustes guardados", Toast.LENGTH_LONG)

                try {
                    val usr=User(
                        binding.tfEmail.text.toString(),
                        binding.tfPassword.text.toString(),
                        binding.tfName.text.toString(),
                        binding.tfSurnames.text.toString(),
                        binding.spCarrera.getItemAtPosition(binding.spCarrera.selectedItemPosition).toString(),
                        false,
                        "defaultIMG")

                    writeNewuser(usr)
                    finish()
                    toast.show()
                }catch (e: ExceptionInInitializerError){
                    print(e)
                }

            }
    }

    fun writeNewuser(user: User){
        database.child("users").child(database.push().key.toString()).setValue(user)
    }


}