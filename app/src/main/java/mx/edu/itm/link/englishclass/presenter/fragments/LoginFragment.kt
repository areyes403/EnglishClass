package mx.edu.itm.link.englishclass.presenter.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.edu.itm.link.englishclass.R
import mx.edu.itm.link.englishclass.databinding.FragmentLoginBinding
import mx.edu.itm.link.englishclass.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.presenter.AuthViewModel
import mx.edu.itm.link.englishclass.presenter.util.snackBar
import mx.edu.itm.link.englishclass.presenter.util.toast

 @AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding:FragmentLoginBinding?=null
    private val binding get() = _binding!!
    private val authViewModel:AuthViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding=FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnLogin.setOnClickListener {
                val email = tfEmailLogin.editText?.text.toString()
                val pass = tfPasswordLogin.editText?.text.toString()
                if (!isValidEmail(email = email)){
                    tfEmailLogin.editText?.error="Ingresa un correo valido"
                    return@setOnClickListener
                }
                if (!isValidPassword(password = pass)){
                    tfPasswordLogin.editText?.error="Contraseña no valida"
                    return@setOnClickListener
                }
                authViewModel.login(user = email, pass = pass)
            }

            txtRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
        observers()
    }

    private fun observers() {
        authViewModel.login.observe(viewLifecycleOwner){response->
            when(response){
                is ResponseStatus.Loading->{
                    Log.i("responseLogin","Loading")
                }
                is ResponseStatus.Error->{
                    snackBar(response.error)
                }
                is ResponseStatus.Success->{
                    toast(response.data)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    private fun isValidEmail(email:String):Boolean{
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        return email.matches(emailRegex.toRegex())
    }

    private fun isValidPassword(password:String):Boolean {
        if (password.length < 8) return false
        if (password.firstOrNull { it.isDigit() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false
        if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false
        return true
    }

}