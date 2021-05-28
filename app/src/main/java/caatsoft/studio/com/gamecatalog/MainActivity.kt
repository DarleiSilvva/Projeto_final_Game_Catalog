package caatsoft.studio.com.gamecatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import caatsoft.studio.com.gamecatalog.databinding.ActivityMainBinding
import caatsoft.studio.com.gamecatalog.network.GameResponse
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent


class MainActivity : AppCompatActivity(), MainView, KoinComponent{
    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        mainViewModel.view = this
        mainViewModel.getFilteredGames()
    }

    override fun getFilteredGames(gameResponse: GameResponse) {
        activityMainBinding.textView.text = gameResponse.results[0].deck

        /*gameResponse2.enqueue(object : Callback<GameResponse2> {
        override fun onResponse(call: Call<GameResponse2>, response: Response<GameResponse2>) {
          response.let {
            val gameResponse:GameResponse2 = it.body()!!
              activityMainBinding.textView.text = gameResponse.results[0].deck

          }
        }
        override fun onFailure(call: Call<GameResponse2>, t: Throwable) {
        }
      })*/
    }
}

