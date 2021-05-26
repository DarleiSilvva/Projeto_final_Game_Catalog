package caatsoft.studio.com.gamecatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import caatsoft.studio.com.gamecatalog.databinding.ActivityMainBinding
import caatsoft.studio.com.gamecatalog.network.GameResponse2
import org.koin.core.KoinComponent


class MainActivity : AppCompatActivity(), MainView, KoinComponent{
    //private val viewModel: MainViewModel by viewModels(),

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.view = this
        viewModel.getFilteredGames()
    }

    override fun getFilteredGames(GameResponse: GameResponse2) {
        activityMainBinding.textView.text = GameResponse.results[0].deck
    }
}