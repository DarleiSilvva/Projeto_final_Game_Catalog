package caatsoft.studio.com.gamecatalog.auth

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import caatsoft.studio.com.gamecatalog.ui.MainActivity
import caatsoft.studio.com.gamecatalog.R
import caatsoft.studio.com.gamecatalog.databinding.ActivityRegisterBinding
import caatsoft.studio.com.gamecatalog.dismissDialog
import caatsoft.studio.com.gamecatalog.network.model.DataUser
import caatsoft.studio.com.gamecatalog.startLoading
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.lang.Exception
import java.util.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var dataUser: DataUser
    private var resultUri: Uri? = null
    private lateinit var activityRegisterBinding: ActivityRegisterBinding
    var verifyPermission = false
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRegisterBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(activityRegisterBinding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference

        applyVerifyPermissions()
        activityRegisterBinding.register.setOnClickListener(View.OnClickListener {
            startLoading()
            alertDialog =  startLoading()
            saveImage()
        })
        activityRegisterBinding.profileImageView.setOnClickListener(View.OnClickListener {
            takePhoto()
        })
    }

    fun takePhoto(){
        if (verifyPermission) {
            val choosePhoto = Intent(Intent.ACTION_GET_CONTENT)
            choosePhoto.type = TYPE_DATA
            startActivityForResult(Intent.createChooser(choosePhoto, getString(R.string.choose_the_photo_for_the_profile)), REQUEST_CODE)
        }else{
            applyVerifyPermissions()
        }
    }
    private fun createUser(photourl:String) {
        val name = activityRegisterBinding.name.text.toString()
        val email = activityRegisterBinding.email.text.toString()
        val password = activityRegisterBinding.password.text.toString()
        if (name == null || name.isEmpty() || email == null
                || email.isEmpty() || password == null || password.isEmpty()|| photourl == null || photourl.isEmpty()) {
            Toast.makeText(this, getString(R.string.all_must_be_filled_in), Toast.LENGTH_SHORT).show()
            dismissDialog(alertDialog)
        } else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            saveUserInFirebase(photourl)
                            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            dismissDialog(alertDialog)
                            finish()
                        } else {
                            Toast.makeText(this@RegisterActivity, getString(R.string.enter_a_valid_email_address), Toast.LENGTH_LONG).show()
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this@RegisterActivity, getString(R.string.enter_a_valid_email_address), Toast.LENGTH_LONG).show()
                    }
        }

    }

    private fun saveUserInFirebase(url: String) {
        val uid = FirebaseAuth.getInstance().uid
        val userName = activityRegisterBinding.name.text.toString()
        val user = User(uid, userName)
        FirebaseFirestore.getInstance().collection(PATH_USER)
                .document(uid!!)
                .set(user)
                .addOnSuccessListener {
                }
                .addOnFailureListener {
                    Toast.makeText(this@RegisterActivity, getString(R.string.register_another_email_or_go_to_the_login_screen_to_see_if_this_one_is_already_registered), Toast.LENGTH_LONG).show()
                }

        dataUser = DataUser(uid, userName, url)
        databaseReference.child(PATH_USER).child(uid.toString())
            .setValue(dataUser)
    }


    private fun saveImage() {
        if (resultUri != null){
            lateinit var bitmap: Bitmap
            try {
                bitmap = MediaStore.Images.Media.getBitmap(application.contentResolver, resultUri)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, QUALITY, baos)
            val data = baos.toByteArray()

            val storage = FirebaseStorage.getInstance()

            val storageRef = storage.reference

            val userImageRef = storageRef.child(PATH_LINK1 + FirebaseAuth.getInstance().uid.toString() + PATH_LINK2)
            var uploadTask = userImageRef.putBytes(data)

            uploadTask = userImageRef.putFile(resultUri!!)

            try {
                val urlTask = uploadTask.continueWithTask { task ->
                    if (!task.isSuccessful) {
                        task.exception?.let {
                            throw it
                        }
                    }
                    userImageRef.downloadUrl
                }.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result
                        createUser(downloadUri.toString())
                    }
                }
            } catch (e: Exception){
                Toast.makeText(baseContext, getString(R.string.erro), Toast.LENGTH_LONG).show()
            }
        } else{
            dismissDialog(alertDialog)
            Toast.makeText(this, getString(R.string.all_must_be_filled_in), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val imageUri: Uri? = data!!.data
            if (imageUri != null) {
                resultUri = imageUri
            }
            activityRegisterBinding.profileImageView.setImageURI(resultUri)
        }
    }

    fun applyVerifyPermissions() {
        val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA)
        permissions.forEach { permission->
            if (ContextCompat.checkSelfPermission(this.applicationContext,
                    permission) == PackageManager.PERMISSION_GRANTED){
                verifyPermission = true
            } else {
                ActivityCompat.requestPermissions(this@RegisterActivity,
                    permissions, REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        applyVerifyPermissions()
    }

    companion object {
        private const val REQUEST_CODE = 1
        private const val QUALITY = 20
        private const val PATH_LINK1 = "User/profile_"
        private const val PATH_LINK2 = ".jpg"
        private const val PATH_USER = "User"
        private const val TYPE_DATA = "image/*"
    }
}