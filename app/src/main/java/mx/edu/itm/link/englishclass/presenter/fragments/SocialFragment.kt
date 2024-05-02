package mx.edu.itm.link.englishclass.presenter.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import mx.edu.itm.link.englishclass.presenter.Adapters.ActiveUsersAdapter
import mx.edu.itm.link.englishclass.profile_feature.domain.model.User
import mx.edu.itm.link.englishclass.R
import kotlin.collections.ArrayList


class SocialFragment : Fragment() {
    //database
    private lateinit var database: DatabaseReference

    //arraylist for users
    private lateinit var usersList: ArrayList<User>
    private lateinit var user: User

    var username = ""
    var friendsUsername = ""
    var isPeerConnected = false
    var firebaseRef = Firebase.database.getReference("calls")
    var callended = Firebase.database.getReference("callsended")
    var isAudio = true
    var isVideo = true


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        usersList= arrayListOf<User>()
        return inflater.inflate(R.layout.fragment_social, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getusers()

        val uid = Firebase.auth.currentUser!!.uid
        username = uid
        //println("username: "+username)

        /*
        toggleAudioBtn.setOnClickListener {
            isAudio = !isAudio
            callJavascriptFunction("javascript:toggleAudio(\"${isAudio}\")")
            toggleAudioBtn.setImageResource(if (isAudio) R.drawable.ic_mic_on else R.drawable.ic_mic_off )
        }

        toggleVideoBtn.setOnClickListener {
            isVideo = !isVideo
            callJavascriptFunction("javascript:toggleVideo(\"${isVideo}\")")
            toggleVideoBtn.setImageResource(if (isVideo) R.drawable.ic_camera_on else R.drawable.ic_camera_off )
        }

        finishCallBtn.setOnClickListener {
            endCall()
            setupWebView()
        }


         */

       // setupWebView()

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
                    refreshContacts(usersList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    //refrescar Recycler
    private fun refreshContacts(list:ArrayList<User>){
        if (list.isNotEmpty()){
            //rvLogeed.visibility=View.VISIBLE
            val uid=Firebase.auth.currentUser!!.uid
            val newList: ArrayList<User> = arrayListOf()
            for (a in list){
                if (a.id!=uid){
                    newList.add(a)
                } else{
                    user=a
                }
            }

            try {
                val adapter= ActiveUsersAdapter(this,R.layout.activeuser_item, newList)
                //rvLogeed.adapter = adapter
                //rvLogeed.layoutManager = LinearLayoutManager(activity)
                adapter.setOnItemClickListener(object : ActiveUsersAdapter.onItemClickListener{
                    override fun onItemClickListener(position: Int) {
                        friendsUsername = newList.get(position).id.toString()
                        sendCallRequest()
                        //callingLayout.visibility=View.VISIBLE
                    }
                })//del onclickadapter

            }catch (e:ExceptionInInitializerError){
                print(e)
            }
        }//del empty lista

        //txtNameCurrentUser.text="Hello ${user.nombre}!! How are you today?"
        //txtStatusCurrentUser.text="Your current status: Active"

    }//refreshcontactos

    private fun sendCallRequest() {
        if (!isPeerConnected) {
            Toast.makeText(activity, "Server is not avilable. Check your internet", Toast.LENGTH_LONG).show()
            return
        }
        firebaseRef.child(friendsUsername).child("incoming").setValue(username)
        firebaseRef.child(friendsUsername).child("isAvailable").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value.toString() == "true") {
                    listenForConnId()
                }
            }
        })
    }

    private fun listenForConnId() {
        firebaseRef.child(friendsUsername).child("connId").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value == null)
                    return
                //switchToControls()
                //callingLayout.visibility=View.INVISIBLE
                //callJavascriptFunction("javascript:startCall(\"${snapshot.value}\")")
            }
        })
    }

    /*
    private fun setupWebView() {

        webView.webChromeClient = object: WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest?) {
                request?.grant(request.resources)
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.settings.mediaPlaybackRequiresUserGesture = false
        webView.addJavascriptInterface(JavascriptInterface(this), "Android")
        loadVideoCall()
    }

    private fun loadVideoCall() {
        val filePath = "file:android_asset/call.html"
        webView.loadUrl(filePath)
        webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                initializePeer()
            }
        }
    }

     */

    var uniqueId = ""

    private fun initializePeer() {

        //uniqueId = getUniqueID()

        //callJavascriptFunction("javascript:init(\"${uniqueId}\")")
        firebaseRef.child(username).child("incoming").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                onCallRequest(snapshot.value as? String)
            }
        })

    }

    private fun onCallRequest(caller: String?) {
        var name=""
        var obj = User()
        var obj2 = User()
        if (caller == null){
            return
        }else{
            for (a in usersList){
                if (caller==a.id){
                    //name=a.nombre.toString()
                    obj=a
                }
            }

            for (a in usersList){
                if (uniqueId==a.id){
                    //name=a.nombre.toString()
                    obj2=a
                }
            }
            /*

            callLayout.visibility = View.VISIBLE
            incomingCallTxt.text = "${obj.nombre!!} is calling..."

            acceptBtn.setOnClickListener {
                firebaseRef.child(username).child("connId").setValue(uniqueId)
                firebaseRef.child(username).child("isAvailable").setValue(true)

                //para guardar el usuario
                val id=UUID.randomUUID().toString()
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())
                callended.child(id).child("receptor").setValue(obj2.nombre)
                callended.child(id).child("emisor").setValue(obj.nombre)
                callended.child(id).child("date").setValue(currentDate)
                callended.child(id).child("idEmisor").setValue(obj.id)
                callended.child(id).child("idReceptor").setValue(obj2.id)
                callLayout.visibility = View.INVISIBLE

                switchToControls()
            }//aceptbtnonClick

            rejectBtn.setOnClickListener {
                firebaseRef.child(username).child("incoming").setValue(null)
                callLayout.visibility = View.INVISIBLE
            }//rejectbtn
            rejectBtnCallingSended.setOnClickListener {
                firebaseRef.child(username).child("incoming").setValue(null)
                callLayout.visibility = View.INVISIBLE
            }

             */
        }//if




    }

    private fun switchToControls() {
        //webView.visibility=View.VISIBLE
        //callControlLayout.visibility = View.VISIBLE
    }

    private fun getUniqueID(): String {
        val uid = Firebase.auth.currentUser!!.uid
        return uid
    }

    private fun callJavascriptFunction(functionString: String) {
       // webView.post { webView.evaluateJavascript(functionString, null) }
    }

    fun onPeerConnected() {
        isPeerConnected = true
    }

    private fun endCall(){
        firebaseRef.child(username).setValue(null)
       // webView.loadUrl("about:blank")
       // webView.visibility=View.INVISIBLE
       // callLayout.visibility=View.INVISIBLE
        //callControlLayout.visibility=View.INVISIBLE
    }

}