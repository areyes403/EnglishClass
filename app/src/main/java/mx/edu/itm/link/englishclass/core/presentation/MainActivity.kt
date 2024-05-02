package mx.edu.itm.link.englishclass.core.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import mx.edu.itm.link.englishclass.R
import mx.edu.itm.link.englishclass.core.utils.hide
import mx.edu.itm.link.englishclass.core.utils.show
import mx.edu.itm.link.englishclass.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    private val authViewModel by viewModels<AuthViewModel>()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHost.navController

        if (authViewModel.user.value == null){
            Log.i("userDetails", "El user es null")
            binding.bottonNavigation.hide()
            navController.setGraph(R.navigation.nav_auth)
        }else{
            Log.i("userDetails", authViewModel.user.value.toString())
            binding.bottonNavigation.show()
            navController.setGraph(R.navigation.nav_home)
        }
    }

}




