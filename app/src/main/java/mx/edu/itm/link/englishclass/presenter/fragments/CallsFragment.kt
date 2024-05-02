package mx.edu.itm.link.englishclass.presenter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import mx.edu.itm.link.englishclass.presenter.Adapters.CallsAdapter
import mx.edu.itm.link.englishclass.call_feature.data.model.Call
import mx.edu.itm.link.englishclass.R
import kotlin.collections.ArrayList



class CallsFragment : Fragment() {

    //arraylist for users
    private var callslist: ArrayList<Call> = arrayListOf<Call>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        getcalls()
        return inflater.inflate(R.layout.fragment_calls, container, false)
    }

    private fun getcalls(){
        var database= FirebaseDatabase.getInstance().getReference("callsended")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                callslist?.clear()
                if(snapshot.exists()){
                    for(callSnap in snapshot.children){
                        val call=callSnap.getValue(Call::class.java)
                        callslist.add(call!!)
                        println("Tamaño de la lista: "+callslist.size)
                    }
                    refreshCalls(callslist)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun refreshCalls(list: ArrayList<Call>) {
        val newList: ArrayList<Call> = arrayListOf()
        val adapter= CallsAdapter(this,R.layout.calls_ended_item, list)
    }
}

