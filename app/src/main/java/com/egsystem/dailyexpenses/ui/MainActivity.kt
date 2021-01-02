package com.egsystem.dailyexpenses.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraphNavigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.egsystem.dailyexpenses.R
import com.egsystem.dailyexpenses.logic.utils.colorAnimation
import com.egsystem.dailyexpenses.ui.fragments.terms_conditions.TermConditionFragment
import com.egsystem.dailyexpenses.ui.fragments.terms_conditions.TermConditionFragmentDirections
import com.egsystem.dailyexpenses.ui.introscreen.IntroActivity
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var userFirstTime = true

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Checks whether it's the users first time
        loadData()

        //If it's the users first time, it will store that the user saw the intro screen, and ignore this the next time
        if (userFirstTime) {
            userFirstTime = false
            saveData()

            val i = Intent(this, IntroActivity::class.java)
            startActivity(i)
            finish()
        }


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.searchFragment, R.id.settingsFragment),
            drawer_layout
        )

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        bottom_nav.setupWithNavController(navController)
        nav_view.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.termsAndConditions) {
            val action = TermConditionFragmentDirections.actionGlobalTermConditionFragment()
            navController.navigate(action)
            true
        } else {
            item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


//    private val menu by lazy { findViewById<ChipNavigationBar>(R.id.bottom_menu) }
//    private var lastColor: Int = 0
//
//    private var userFirstTime = true
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        //Checks whether it's the users first time
//        loadData()
//
//        //If it's the users first time, it will store that the user saw the intro screen, and ignore this the next time
//        if (userFirstTime) {
//            userFirstTime = false
//            saveData()
//
//            val i = Intent(this, IntroActivity::class.java)
//            startActivity(i)
//            finish()
//        }
//
//        setupActionBarWithNavController(findNavController(R.id.navHostFragment))
//
//
//        bottomNavigation()
//    }

//    private fun bottomNavigation() {
//
//        menu.setOnItemSelectedListener { id ->
//            val option = when (id) {
//                R.id.home -> R.color.home to "Home"
////                R.id.activity -> {
////                    R.color.activity to "Activity"
////                    findNavController().navigate(R.id.action_homeFragment_to_incomeListFragment)
////                }
////                R.id.favorites -> R.color.favorites to "Favorites"
//                R.id.settings -> R.color.settings to "Settings"
////                R.id.settings ->  setupActionBarWithNavController(findNavController(R.id.navHostFragment))
//                else -> R.color.white to ""
//            }
//            val color = ContextCompat.getColor(this@MainActivity, option.first)
//            container.colorAnimation(lastColor, color)
//            lastColor = color
//
////                 title.text = option.second
//        }
//
//
////             if (savedInstanceState == null) {
////                 menu.showBadge(R.id.home)
////                 menu.showBadge(R.id.settings, 32)
////             }
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.navHostFragment)
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }


    private fun saveData() {
        val sp = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
        sp.edit().apply {
            putBoolean("BOOLEAN_FIRST_TIME", userFirstTime)
            apply()
        }
    }

    private fun loadData() {
        val sp = getSharedPreferences("SHARED_PREFS", MODE_PRIVATE)
        userFirstTime = sp.getBoolean("BOOLEAN_FIRST_TIME", true)
    }

}