package caatsoft.studio.com.gamecatalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import caatsoft.studio.com.gamecatalog.auth.LoginActivity
import caatsoft.studio.com.gamecatalog.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var firebaseAuthStateListener: FirebaseAuth.AuthStateListener
    var initValidation = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        mAuth = FirebaseAuth.getInstance()

        firebaseAuthStateListener = FirebaseAuth.AuthStateListener {
            val user = FirebaseAuth.getInstance().currentUser
            if (user == null) {
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
                finish()
                return@AuthStateListener
            } else {
                if (initValidation == 0) {
                    val navView: BottomNavigationView = activityMainBinding.navView

                    val navController = findNavController(R.id.nav_host_fragment_activity_main)
                    val appBarConfiguration = AppBarConfiguration(
                        setOf(
                            R.id.navigation_games, R.id.navigation_favorite, R.id.navigation_user
                        )
                    )
                    setupActionBarWithNavController(navController, appBarConfiguration)
                    navView.setupWithNavController(navController)
                    initValidation = 1
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(firebaseAuthStateListener)
    }

    override fun onStop() {
        super.onStop()
        mAuth.removeAuthStateListener(firebaseAuthStateListener)
    }
}