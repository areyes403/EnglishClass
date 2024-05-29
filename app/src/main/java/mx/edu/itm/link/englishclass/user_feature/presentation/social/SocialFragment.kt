package mx.edu.itm.link.englishclass.user_feature.presentation.social


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mx.edu.itm.link.englishclass.R
import mx.edu.itm.link.englishclass.core.domain.model.GeneralId
import mx.edu.itm.link.englishclass.user_feature.presentation.social.adapter.AdapterActiveUsers
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.core.utils.snackBar
import mx.edu.itm.link.englishclass.databinding.FragmentSocialBinding

@AndroidEntryPoint
class SocialFragment : Fragment() {

    private var _binding:FragmentSocialBinding?=null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SocialViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding= FragmentSocialBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observers()

        askNotificationPermission()
    }

    private fun observers() {
        viewModel.activeUsers.onEach { response->
            when(response){
                is ResponseStatus.Loading->{
                }
                is ResponseStatus.Success->{
                    val adapter=AdapterActiveUsers(response.data) { selectedUser->
                        val receptor=GeneralId(id = selectedUser.id, name = selectedUser.name)
                        val emisor=GeneralId(id = viewModel.user.value!!.id,viewModel.user.value!!.name)

                        val args= bundleOf(
                            "receptor" to receptor,
                            "emisor" to emisor
                        )

                        findNavController().navigate(R.id.action_socialFragment_to_videoCallFragment , args)
                    }

                    binding.rvUsers.layoutManager=LinearLayoutManager(requireContext())
                    binding.rvUsers.adapter=adapter

                }
                is ResponseStatus.Error->{
                    snackBar(response.error)
                }
            }

        }.launchIn(lifecycleScope)

        viewModel.user.observe(viewLifecycleOwner){
            binding.txtNameCurrentUser.text="Hola: "+it.name
            binding.txtStatusCurrentUser.text="Status: Conectado"
        }

    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

}