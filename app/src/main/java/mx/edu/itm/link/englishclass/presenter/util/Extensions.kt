package mx.edu.itm.link.englishclass.presenter.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.snackBar(msg:String?){
    view?.let {
        Snackbar.make(it,msg.toString(),Snackbar.LENGTH_LONG).show()
    }
}

fun Fragment.toast(msg: String?){
    Toast.makeText(requireContext(),msg.toString(),Toast.LENGTH_LONG).show()
}