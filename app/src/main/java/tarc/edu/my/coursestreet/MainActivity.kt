package tarc.edu.my.coursestreet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import tarc.edu.my.coursestreet.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val nav by lazy { supportFragmentManager.findFragmentById(R.id.host)!!.findNavController() }
    private lateinit var appBarConf: AppBarConfiguration

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