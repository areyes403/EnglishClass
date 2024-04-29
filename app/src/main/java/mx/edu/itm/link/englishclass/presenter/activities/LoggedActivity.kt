package mx.edu.itm.link.englishclass.presenter.activities

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import mx.edu.itm.link.englishclass.R
import mx.edu.itm.link.englishclass.presenter.fragments.CallsFragment
import mx.edu.itm.link.englishclass.presenter.fragments.SettingsFragment
import mx.edu.itm.link.englishclass.presenter.fragments.SocialFragment


class LoggedActivity : AppCompatActivity() {

    private val permissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    private val requestcode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)

        if (!isPermissionGranted()) {
            askPermissions()
        }

        val socialFragment = SocialFragment()
        val settingsFragment = SettingsFragment()
        val callFragment = CallsFragment()
        makeCurrentFragment(socialFragment)

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }

    private fun askPermissions() {
        ActivityCompat.requestPermissions(this, permissions, requestcode)
    }

    private fun isPermissionGranted(): Boolean {
        permissions.forEach {
            if (ActivityCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED)
                return false
        }
        return true
    }




}