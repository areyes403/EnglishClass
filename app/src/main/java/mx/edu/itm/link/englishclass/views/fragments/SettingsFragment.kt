package mx.edu.itm.link.englishclass.views.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_settings.*
import mx.edu.itm.link.englishclass.Data.User
import mx.edu.itm.link.englishclass.R
import mx.edu.itm.link.englishclass.views.activities.EditProfileActivity
import mx.edu.itm.link.englishclass.views.activities.RegisterActivity


class SettingsFragment : Fragment() {

    //database
    private lateinit var database: DatabaseReference

    //arraylist for users
    private var usersList: ArrayList<User> = arrayListOf<User>()
    private var user: User = User()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getusers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
        println("Soy:"+user.nombre)
    }

    //consultar usuarios
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

        txtNameSettings.text="Name: ${user.nombre}"
        txtSurnamesSettings.text="Surnames: ${user.apellidos}"
        txtCarreraSettings.text="Academic Major: ${user.carrera}"
        txtEmailSettings.text="Email: ${user.correo}"

        btnEditSettings.setOnClickListener{
            startActivity(Intent(activity, EditProfileActivity::class.java))
        }

    }


}