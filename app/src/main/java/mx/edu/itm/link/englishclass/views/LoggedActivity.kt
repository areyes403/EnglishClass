package mx.edu.itm.link.englishclass.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import mx.edu.itm.link.englishclass.R
import mx.edu.itm.link.englishclass.databinding.ActivityLoggedBinding

class LoggedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoggedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoggedBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.rvLogeed.layoutManager= LinearLayoutManager(this)
    }
}