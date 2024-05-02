package mx.edu.itm.link.englishclass.core.utils

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

fun View.hide(){
    visibility = View.GONE
}

fun View.show(){
    visibility = View.VISIBLE
}

fun String.checkIfIsValidEmail():Boolean{
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    return this.matches(emailRegex.toRegex())
}

fun String.checkIfIsValidPassword():Boolean{
    if (this.length < 8) return false
    if (this.firstOrNull { it.isDigit() } == null) return false
    if (this.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
    if (this.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false
    if (this.firstOrNull { !it.isLetterOrDigit() } == null) return false
    return true
}