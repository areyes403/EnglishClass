package mx.edu.itm.link.englishclass.call_feature.presentation.realtime_videocall

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mx.edu.itm.link.englishclass.R
import mx.edu.itm.link.englishclass.call_feature.domain.model.CallState
import mx.edu.itm.link.englishclass.core.domain.model.GeneralId
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.core.utils.ARGS
import mx.edu.itm.link.englishclass.core.utils.show
import mx.edu.itm.link.englishclass.core.utils.snackBar
import mx.edu.itm.link.englishclass.databinding.FragmentVideocallBinding
import mx.edu.itm.link.englishclass.user_feature.domain.model.User

class VideoCallFragment : Fragment() {

    private var _binding:FragmentVideocallBinding?=null
    private val binding get() = _binding!!

    private var isPeerConnected = false
    private var isAudio = true
    private var isVideo = true

    private var imReceptor=false

    private val model by viewModels<VideoCallViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        _binding=FragmentVideocallBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupWebView()

        arguments?.let {
            val emisor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                it.getParcelable("emisor",GeneralId::class.java)
            }else null

            val receptor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                it.getParcelable("receptor",GeneralId::class.java)
            }else null

            model.setCallUID(id = it.getString(ARGS.CALL_UID,""), e = emisor, r = receptor)
        }

        observers()
    }

    private fun sendCallRequest() {
        if (!isPeerConnected) {
            Toast.makeText(activity, "Server is not avilable. Check your internet", Toast.LENGTH_LONG).show()
            return
        }
        /*
        firebaseRef.child(friendsUsername).child("incoming").setValue(viewModel.fbUser.uid)
        firebaseRef.child(friendsUsername).child("isAvailable").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.value.toString() == "true") {
                    listenForConnId()
                }
            }
        })

         */
    }

    private fun loadVideoCall() {
        val filePath = "file:android_asset/call.html"
        binding.webView.loadUrl(filePath)
        binding.webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                initializePeer()
            }
        }
    }

    private fun initializePeer() {

        callJavascriptFunction("javascript:init(\"${model.callUID}\")")
        /*
        firebaseRef.child(viewModel.fbUser.uid).child("incoming").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}
            override fun onDataChange(snapshot: DataSnapshot) {
                onCallRequest(snapshot.value as? String)
            }
        })

         */

    }

    private fun callJavascriptFunction(functionString: String) {
        binding.webView.post {
            binding.webView.evaluateJavascript(functionString, null)
        }
    }

    fun onPeerConnected() {
        isPeerConnected = true
    }

    private fun observers() {
        model.videoRealtimeCall.onEach { observer->
            when(observer){
                is ResponseStatus.Loading->{

                }
                is ResponseStatus.Success->{
                    imReceptor= observer.data.receptor!!.id == model.user.id

                    when(observer.data.state){
                        CallState.CONNECTING.toString()->{
                            if (imReceptor){
                                binding.incomingCallLayout.show()
                                //binding.imgProfilewhileConnect
                                binding.acceptBtn.setOnClickListener {

                                }
                                binding.rejectBtn.setOnClickListener {

                                }
                            }else{
                                binding.outgoingCallLayout.show()

                                binding.rejectBtnCallingSended.setOnClickListener {

                                }
                            }
                        }
                        CallState.CONNECTED.toString()->{
                            binding.callControlLayout.show()
                            binding.apply {
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
                                    model.endCall()
                                }
                            }

                        }
                        CallState.DISCONNECTED.toString()->{

                        }
                        CallState.FAILED.toString() ->{

                        }
                    }
                }
                is ResponseStatus.Error->{
                    snackBar(observer.error)
                }
            }
        }.launchIn(lifecycleScope)

        model.requestCall.observe(requireActivity()){response->
            when(response){
                is ResponseStatus.Loading->{

                }
                is ResponseStatus.Success->{
                    model.setCallUID(id = response.data,e = null,r = null)
                }
                is ResponseStatus.Error->{

                }
            }
        }
    }

    private fun setupWebView() {
        binding.apply {
            webView.webChromeClient = object: WebChromeClient() {
                override fun onPermissionRequest(request: PermissionRequest?) {
                    request?.grant(request.resources)
                }
            }
            webView.settings.javaScriptEnabled = true
            webView.settings.mediaPlaybackRequiresUserGesture = false
            webView.addJavascriptInterface(JavascriptInterface(this@VideoCallFragment), "Android")
            loadVideoCall()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}