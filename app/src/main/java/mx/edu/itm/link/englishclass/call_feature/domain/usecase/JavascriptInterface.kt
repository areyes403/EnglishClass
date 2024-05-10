package mx.edu.itm.link.englishclass.call_feature.domain.usecase

import android.webkit.JavascriptInterface
import mx.edu.itm.link.englishclass.call_feature.presentation.realtime_videocall.VideoCallFragment
import mx.edu.itm.link.englishclass.user_feature.presentation.social.SocialFragment

class JavascriptInterface(val fragment: VideoCallFragment) {
    @JavascriptInterface
    public fun onPeerConnected() {
        //callActivity.onPeerConnected()
        fragment.onPeerConnected()
    }
}