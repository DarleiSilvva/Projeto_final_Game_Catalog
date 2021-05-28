package caatsoft.studio.com.gamecatalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import caatsoft.studio.com.gamecatalog.databinding.ActivityMainBinding
import caatsoft.studio.com.gamecatalog.network.GameResponse
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent


class MainActivity : AppCompatActivity(), KoinComponent, MainView {
    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        //mainViewModel.view = this
        //mainViewModel.getFilteredGames()

        mainViewModel.games.observe(this, { gamesList->
            activityMainBinding.textView.text = gamesList[0].deck
        })
    }

    override fun getFilteredGames(gameResponse: GameResponse) {
        //activityMainBinding.textView.text = gameResponse[0].deck
    }
}

