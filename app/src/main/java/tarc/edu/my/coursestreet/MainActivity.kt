package tarc.edu.my.coursestreet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.graphics.drawable.toDrawable
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
        if (auth.noEmail(this@MainActivity)){
            supportActionBar?.hide()
            nav.navigate(R.id.homeFragment)
            nav.navigate(R.id.action_homeFragment_to_loginFragment)
        }

        auth.getUserLiveData().observe(this) { user ->

            binding.navView.menu.clear()
            binding.drawerLayout.close()

            if (user == null){
                supportActionBar?.hide()
                nav.navigate(R.id.homeFragment)
                nav.navigate(R.id.action_homeFragment_to_loginFragment)
            }else{
                if(user.results){
                supportActionBar?.show()
                binding.navView.inflateMenu(R.menu.nav_menu)
                setHeader(user)
               }else{
                    supportActionBar?.hide()
                    nav.popBackStack(R.id.nav_graph,true)
                    nav.navigate(R.id.loginFragment)
                    nav.navigate(R.id.action_loginFragment_to_resultsFragment)
                }

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

        if(user.photo != Blob.fromBytes(ByteArray(0))) {
            b.imageView.setImageBlob(user.photo)
        }else{
            b.imageView.setImageResource(R.drawable.vector_account)
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