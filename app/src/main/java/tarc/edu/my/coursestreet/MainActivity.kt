package tarc.edu.my.coursestreet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import tarc.edu.my.coursestreet.data.AuthViewModel
import tarc.edu.my.coursestreet.data.User
import tarc.edu.my.coursestreet.databinding.ActivityMainBinding
import tarc.edu.my.coursestreet.databinding.HeaderBinding
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

        // TODO:LOGIN
//        if (auth.getUser() == null){
//            nav.navigate(R.id.loginFragment)
//        }
//
//        auth.getUserLiveData().observe(this) { user ->
//
//            binding.navView.menu.clear()
//            binding.drawerLayout.close()
//
//            if (user == null){
//                nav.navigate(R.id.loginFragment)
//            }else{
//                appBarConf = AppBarConfiguration(
//                    setOf(
//                        R.id.homeFragment,
//                        R.id.forumFragment,
//                        R.id.storeFragment,
//                        R.id.profileFragment
//                    ),
//                    binding.drawerLayout
//                )
//                setupActionBarWithNavController(nav, appBarConf)
//                binding.navView.setupWithNavController(nav)
//
//                binding.navView.inflateMenu(R.menu.nav_menu)
//                setHeader(user)
//
//                binding.navView.menu.findItem(R.id.logout)?.setOnMenuItemClickListener { logout() }
//            }
//
//        }

    }

    private fun logout(): Boolean {
        // TODO(4): Logout -> auth.logout(...)
        //          Clear navigation backstack
        auth.logout(this)
        nav.popBackStack(R.id.loginFragment,false)
        nav.navigate(R.id.loginFragment)
        return true
    }

    private fun setHeader(user: User) {
        val h = binding.navView.getHeaderView(0)
        val b = HeaderBinding.bind(h)

        b.imageView.setImageBlob(user.photo)
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