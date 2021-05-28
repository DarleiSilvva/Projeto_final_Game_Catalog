package caatsoft.studio.com.gamecatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import caatsoft.studio.com.gamecatalog.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        mainViewModel.games.observe(this, { gamesList ->
            activityMainBinding.textView.text = gamesList[0].deck
        })
        mainViewModel.getFilteredGames()
    }
}

