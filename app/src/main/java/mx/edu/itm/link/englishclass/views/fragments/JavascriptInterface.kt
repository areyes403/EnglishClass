package mx.edu.itm.link.englishclass.views.fragments

import android.webkit.JavascriptInterface
import mx.edu.itm.link.englishclass.views.activities.LoggedActivity

class JavascriptInterface(val socialFragment: SocialFragment) {
    @JavascriptInterface
    public fun onPeerConnected() {
        //callActivity.onPeerConnected()
        socialFragment.onPeerConnected()
    }
}