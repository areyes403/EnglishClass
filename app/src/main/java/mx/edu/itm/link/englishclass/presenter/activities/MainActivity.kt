package mx.edu.itm.link.englishclass.presenter.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import mx.edu.itm.link.englishclass.databinding.ActivityMainBinding
import mx.edu.itm.link.englishclass.presenter.AuthViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {

        }


    }

    override fun onStart() {
        super.onStart()
        authViewModel.user.onEach {
            if (it==null){
                Log.i("authData","El usuario es null")
            }else{
                Log.i("authData",it.toString())
            }
        }.launchIn(lifecycleScope)
    }




}




