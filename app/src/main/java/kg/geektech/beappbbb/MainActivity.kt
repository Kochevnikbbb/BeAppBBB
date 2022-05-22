package kg.geektech.beappbbb

import android.view.*
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kg.geektech.beappbbb.base.BaseActivity
import kg.geektech.beappbbb.base.BaseViewModel
import kg.geektech.beappbbb.databinding.ActivityMainBinding

class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {
    private lateinit var appBarConfiguration2: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar


    override val viewModel: BaseViewModel by lazy {
        ViewModelProvider(this)[BaseViewModel::class.java]
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initView() {
        //инициализация actionBar
        val toolbar = binding.contentMain.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //инициализация bottom navigation
        val navViewBottom: BottomNavigationView = binding.contentMain.navView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.recordsFragment, R.id.salonsFragment, R.id.accountsFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navViewBottom.setupWithNavController(navController)

        //инициализация drawer navigation
        drawerLayout = binding.drawerLayout
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarToggle.syncState()
        navView = binding.navViewMenu

    }

    override fun initListener() {
        //клики drawerLayout
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_account -> {
                    Toast.makeText(this, "People", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_records -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }

    fun openDrawer() {
        drawerLayout.open()
    }


    // override the onBackPressed() function to close the Drawer when the back button is clicked
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}