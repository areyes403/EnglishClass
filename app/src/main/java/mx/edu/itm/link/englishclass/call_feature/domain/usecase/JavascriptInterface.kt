package mx.edu.itm.link.englishclass.call_feature.domain.usecase

import android.webkit.JavascriptInterface
import mx.edu.itm.link.englishclass.user_feature.presentation.social.SocialFragment

class JavascriptInterface(val socialFragment: SocialFragment) {
    @JavascriptInterface
    public fun onPeerConnected() {
        //callActivity.onPeerConnected()
        socialFragment.onPeerConnected()
    }
}