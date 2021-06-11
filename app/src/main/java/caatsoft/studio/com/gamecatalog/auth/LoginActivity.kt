package caatsoft.studio.com.gamecatalog.auth

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import caatsoft.studio.com.gamecatalog.ui.MainActivity
import caatsoft.studio.com.gamecatalog.R
import caatsoft.studio.com.gamecatalog.databinding.ActivityLoginBinding
import caatsoft.studio.com.gamecatalog.dismissDialog
import caatsoft.studio.com.gamecatalog.startLoading
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var firebaseAuthStateListener: AuthStateListener
    private lateinit var activityLoginBinding: ActivityLoginBinding
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(activityLoginBinding.root)
        mAuth = FirebaseAuth.getInstance()

        firebaseAuthStateListener = AuthStateListener {
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                return@AuthStateListener
            }
        }

        verifyPermissions()

        activityLoginBinding.accountTextView.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
            return@OnClickListener
        })

        activityLoginBinding.loginButton.setOnClickListener {
            alertDialog = startLoading()
            val email = activityLoginBinding.emailEditView.text.toString()
            val password = activityLoginBinding.passwordEditView.text.toString()
            if (email.isEmpty() || email == null ||
                    password.isEmpty() || password == null) {
                dismissDialog(alertDialog)
                Toast.makeText(this@LoginActivity, getString(R.string.please_fill_in_what_is_missing), Toast.LENGTH_SHORT).show()
            } else {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this@LoginActivity) { task ->
                    if (!task.isSuccessful) {
                        dismissDialog(alertDialog)
                        Toast.makeText(this@LoginActivity, getString(R.string.there_was_an_authentication_error_please_try_to_register_again_or_try_to_login), Toast.LENGTH_SHORT).show()
                    } else{
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        dismissDialog(alertDialog)
                        finish()
                    }
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

    fun verifyPermissions() {
        val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA)
        permissions.forEach { permission->
            if (ContextCompat.checkSelfPermission(this.applicationContext,
                    permission) == PackageManager.PERMISSION_GRANTED){
            } else {
                ActivityCompat.requestPermissions(this@LoginActivity,
                    permissions, REQUEST_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        verifyPermissions()
    }

    companion object {
        private const val REQUEST_CODE = 1
    }
}