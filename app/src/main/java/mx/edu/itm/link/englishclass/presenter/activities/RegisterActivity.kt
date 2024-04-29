package mx.edu.itm.link.englishclass.presenter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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

    }

}