package caatsoft.studio.com.gamecatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import caatsoft.studio.com.gamecatalog.databinding.ActivityMainBinding
import caatsoft.studio.com.gamecatalog.network.GameResponse2
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent


class MainActivity : AppCompatActivity(), MainView, KoinComponent{
    private val mainViewModel: MainViewModel by viewModel()

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        mainViewModel.view = this
        mainViewModel.getFilteredGames()
    }

    override fun getFilteredGames(GameResponse: GameResponse2) {
        activityMainBinding.textView.text = GameResponse.results[0].deck
    }
}