package mx.edu.itm.link.englishclass.presenter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import mx.edu.itm.link.englishclass.data.model.User
import mx.edu.itm.link.englishclass.R

class EditProfileActivity : AppCompatActivity() {

    //database
    private lateinit var database: DatabaseReference

    //arraylist for users
    private var usersList: ArrayList<User> = arrayListOf<User>()
    private var user: User = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        getusers()

        /*
        btnSaveEdited.setOnClickListener{
            database= FirebaseDatabase.getInstance().getReference("users")
            val uid = Firebase.auth.currentUser!!.uid
            database.child(uid).child("nombre").setValue(tfNameEditting.text.toString())
            database.child(uid).child("apellidos").setValue(tfSurnamesEditting.text.toString())

            database.child(uid).child("correo").setValue(tfEmailEditing.text.toString())


            finish()
        }

         */
    }

    private fun getusers(){
        database= FirebaseDatabase.getInstance().getReference("users")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                usersList?.clear()
                if(snapshot.exists()){
                    for(userSnap in snapshot.children){
                        val userData=userSnap.getValue(User::class.java)
                        usersList.add(userData!!)
                    }
                    getaUser(usersList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun getaUser(list: ArrayList<User>) {
        for (a in list){
            //println("Soy el usuario: "+a.nombre)
            if (a.id== Firebase.auth.currentUser!!.uid){
                //println("fui el elegido :"+a.id)
                user=a
            }
        }

    }
}