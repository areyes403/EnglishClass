package mx.edu.itm.link.englishclass.presenter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import mx.edu.itm.link.englishclass.profile_feature.domain.model.User
import mx.edu.itm.link.englishclass.R


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



    }


}