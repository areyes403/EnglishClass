package mx.edu.itm.link.englishclass.call_feature.presentation.realtime_videocall

import android.webkit.JavascriptInterface

class JavascriptInterface(val fragment: VideoCallFragment) {
    @JavascriptInterface
    public fun onPeerConnected() {
        //callActivity.onPeerConnected()
        fragment.onPeerConnected()
    }
}