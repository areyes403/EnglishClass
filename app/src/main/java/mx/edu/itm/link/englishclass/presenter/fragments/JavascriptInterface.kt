package mx.edu.itm.link.englishclass.presenter.fragments

import android.webkit.JavascriptInterface

class JavascriptInterface(val socialFragment: SocialFragment) {
    @JavascriptInterface
    public fun onPeerConnected() {
        //callActivity.onPeerConnected()
        socialFragment.onPeerConnected()
    }
}