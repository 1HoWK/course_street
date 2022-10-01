package tarc.edu.my.coursestreet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.google.firebase.firestore.Blob
import kotlinx.coroutines.launch
import tarc.edu.my.coursestreet.data.AuthViewModel
import tarc.edu.my.coursestreet.data.User
import tarc.edu.my.coursestreet.databinding.ActivityMainBinding
import tarc.edu.my.coursestreet.databinding.HeaderBinding
import tarc.edu.my.coursestreet.ui.LoginFragment
import tarc.edu.my.coursestreet.ui.homeFragment
import tarc.edu.my.coursestreet.util.setImageBlob


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val nav by lazy { supportFragmentManager.findFragmentById(R.id.host)!!.findNavController() }
    private lateinit var appBarConf: AppBarConfiguration
    private val auth: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        appBarConf = AppBarConfiguration(
//            setOf(
//                R.id.homeFragment,
//                R.id.forumFragment,
//                R.id.storeFragment,
//                R.id.profileFragment
//            ),
//            binding.drawerLayout
//        )
//        setupActionBarWithNavController(nav, appBarConf)
//        binding.navView.setupWithNavController(nav)
//
//        if(auth.getUser() == null){
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }

        // TODO:LOGIN
        if (auth.getEmail(this@MainActivity)){
            nav.navigate(R.id.homeFragment)
            nav.navigate(R.id.action_homeFragment_to_loginFragment)
        }

//        if (auth.getUser() == null){
//            nav.navigate(R.id.homeFragment)
//            nav.navigate(R.id.action_homeFragment_to_loginFragment)
//            nav.navigate(R.id.loginFragment)
//            val loginFragment = LoginFragment()
//            supportFragmentManager.beginTransaction().replace(R.id.host, loginFragment).commit()
//        }

        auth.getUserLiveData().observe(this) { user ->
            binding.navView.menu.clear()
            binding.drawerLayout.close()
            if (user == null){
                supportActionBar?.hide()
                nav.navigate(R.id.homeFragment)
                nav.navigate(R.id.action_homeFragment_to_loginFragment)
            }else{
                nav.navigate(R.id.homeFragment)
                supportActionBar?.show()
                appBarConf = AppBarConfiguration(
                    setOf(
                        R.id.homeFragment,
                        R.id.forumFragment,
                        R.id.storeFragment,
                        R.id.profileFragment
                    ),
                    binding.drawerLayout
                )
                setupActionBarWithNavController(nav, appBarConf)
                binding.navView.setupWithNavController(nav)

                binding.navView.inflateMenu(R.menu.nav_menu)
                setHeader(user)
            }
            binding.navView.menu.findItem(R.id.logout)?.setOnMenuItemClickListener { logout() }

        }

        lifecycleScope.launch{ auth.loginFromPreferences(this@MainActivity)}

    }

    private fun logout(): Boolean {
        // TODO(4): Logout -> auth.logout(...)
        //          Clear navigation backstack
        auth.logout(this)
        nav.navigate(R.id.homeFragment)
        nav.navigate(R.id.action_homeFragment_to_loginFragment)
        return true
    }

    private fun setHeader(user: User) {
        val h = binding.navView.getHeaderView(0)
        val b = HeaderBinding.bind(h)

        if(auth.getUser()?.photo != Blob.fromBytes(ByteArray(0))) {
            b.imageView.setImageBlob(user.photo)
        }
        b.textView5.text  = user.name
        b.textView6.text = user.email
    }

    override fun onSupportNavigateUp(): Boolean {
        return nav.navigateUp(appBarConf) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return item.onNavDestinationSelected(nav) || super.onOptionsItemSelected(item)
    }

}