package caatsoft.studio.com.gamecatalog.ui

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import caatsoft.studio.com.gamecatalog.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        val logoLauncher = LogoLauncher()
        logoLauncher.start()
    }

    private inner class LogoLauncher : Thread() {
        override fun run() {
            try {
                sleep((1000 * SLEEP_TIMER).toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
           val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        private const val SLEEP_TIMER = 0.75f
    }

}