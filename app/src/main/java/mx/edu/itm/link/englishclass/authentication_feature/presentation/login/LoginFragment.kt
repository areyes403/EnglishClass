package mx.edu.itm.link.englishclass.authentication_feature.presentation.login

import android.content.Context
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
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.core.utils.checkIfIsValidEmail
import mx.edu.itm.link.englishclass.core.utils.checkIfIsValidPassword
import mx.edu.itm.link.englishclass.core.utils.snackBar
import mx.edu.itm.link.englishclass.core.utils.toast

 @AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding:FragmentLoginBinding?=null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

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
                if (!email.checkIfIsValidEmail()){
                    tfEmailLogin.editText?.error="Ingresa un correo valido"
                    return@setOnClickListener
                }
                if (!pass.checkIfIsValidPassword()){
                    tfPasswordLogin.editText?.error="Contraseña no valida"
                    return@setOnClickListener
                }
                viewModel.login(user = email, pass = pass)
            }

            txtRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
        observers()
    }

    private fun observers() {
        viewModel.login.observe(viewLifecycleOwner){response->
            when(response){
                is ResponseStatus.Loading->{
                    Log.i("responseLogin","Loading")
                }
                is ResponseStatus.Error->{
                    snackBar(response.error)
                }
                is ResponseStatus.Success->{
                    //toast(response.data)
                    //Log.i("user",response.data.toString())
                    viewModel.updateToken(user = response.data)
                    findNavController().setGraph(R.navigation.nav_home)
                }
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}