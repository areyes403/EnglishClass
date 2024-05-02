package mx.edu.itm.link.englishclass.authentication_feature.presentation.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.core.utils.snackBar
import mx.edu.itm.link.englishclass.databinding.FragmentRegisterBinding

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

            }
        }
    }

    private fun observers() {
        viewModel.registration.observe(viewLifecycleOwner){response->
            when(response){
                is ResponseStatus.Loading->{

                }
                is ResponseStatus.Success->{

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