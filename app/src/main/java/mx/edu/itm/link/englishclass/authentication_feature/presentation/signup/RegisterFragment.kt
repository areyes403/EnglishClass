package mx.edu.itm.link.englishclass.authentication_feature.presentation.signup

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
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.core.utils.checkIfIsValidEmail
import mx.edu.itm.link.englishclass.core.utils.checkIfIsValidPassword
import mx.edu.itm.link.englishclass.core.utils.snackBar
import mx.edu.itm.link.englishclass.core.utils.toast
import mx.edu.itm.link.englishclass.databinding.FragmentRegisterBinding
import mx.edu.itm.link.englishclass.user_feature.domain.model.User

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding:FragmentRegisterBinding?=null
    private val binding get() = _binding!!
    private val viewModel:RegisterViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding=FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()




        binding.apply {
            btnRegister.setOnClickListener {
                val prof=binding.menu.editText?.text.toString()
                val email= tfEmail.editText?.text.toString()
                val pass = tfPassword.editText?.text.toString()

                if(!email.checkIfIsValidEmail()){
                    tfEmail.editText?.error="Ingresa un correo valido"
                    return@setOnClickListener
                }

                if (!pass.checkIfIsValidPassword()){
                    tfPassword.editText?.error="Ingresa un correo valido"
                    return@setOnClickListener
                }

                viewModel.register(
                    email = email,
                    password = pass,
                    userData = User(
                        name = tfName.editText?.text.toString(),
                        surNames = tfSurnames.editText?.text.toString(),
                        profession = prof
                    )
                )
            }
        }
    }

    private fun observers() {
        viewModel.registration.observe(viewLifecycleOwner){response->
            when(response){
                is ResponseStatus.Loading->{

                }
                is ResponseStatus.Success->{
                    findNavController().setGraph(R.navigation.nav_home)
                }
                is ResponseStatus.Error->{
                    snackBar(response.error)
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}