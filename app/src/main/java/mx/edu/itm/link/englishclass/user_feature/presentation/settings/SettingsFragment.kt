package mx.edu.itm.link.englishclass.user_feature.presentation.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.edu.itm.link.englishclass.databinding.FragmentSettingsBinding

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding:FragmentSettingsBinding?=null
    private val binding get() = _binding!!

    private val model by viewModels<SettingsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding=FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
    }

    private fun observers() {
        model.user.observe(requireActivity()){
            binding.txtNameSettings.text="Nombre(s): "+it.name
            binding.txtCarreraSettings.text="Carrera: "+it.profession
            binding.txtSurnamesSettings.text="Appellidos: "+it.surNames
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}