package mx.edu.itm.link.englishclass.presenter.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.edu.itm.link.englishclass.R
import mx.edu.itm.link.englishclass.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding:FragmentRegisterBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding=FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}