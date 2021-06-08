package caatsoft.studio.com.gamecatalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import caatsoft.studio.com.gamecatalog.GameCatalog
import caatsoft.studio.com.gamecatalog.R
import caatsoft.studio.com.gamecatalog.adapter.FavoriteGameAdapter
import caatsoft.studio.com.gamecatalog.adapter.GameAdapter
import caatsoft.studio.com.gamecatalog.databinding.BottomModelBinding
import caatsoft.studio.com.gamecatalog.databinding.FragmentGamesBinding
import caatsoft.studio.com.gamecatalog.network.model.Game
import caatsoft.studio.com.gamecatalog.network.model.FavoriteGame
import caatsoft.studio.com.gamecatalog.repository.FavoriteRepositoryImpl
import caatsoft.studio.com.gamecatalog.viewmodel.FavoriteViewModel
import caatsoft.studio.com.gamecatalog.viewmodel.GamesViewModel
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class GamesFragment : Fragment(), GameAdapter.GameClickListener, KoinComponent {

    private var _binding: FragmentGamesBinding? = null
    private val gamesViewModel: GamesViewModel by viewModel()
    private lateinit var gameAdapter: GameAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGamesBinding.inflate(inflater, container, false)

        gamesViewModel.games.observe(viewLifecycleOwner, { gamesList ->
            val mLayoutManager = GridLayoutManager( this.context, 2)
            _binding?.gameRecyclerview?.layoutManager = mLayoutManager;
            gameAdapter = GameAdapter(gamesList, this)
            _binding?.gameRecyclerview?.adapter = gameAdapter
        })
        gamesViewModel.getFilteredGames()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onGameClicked(game: Game) {
        val bottomModelBinding: BottomModelBinding = BottomModelBinding.inflate(layoutInflater)

        val dialog = BottomSheetDialog (requireContext())
        dialog.setContentView (bottomModelBinding.root)
        bottomModelBinding.nameText.text = game.name
        Glide.with(requireContext()).load(game.image.screen_large_url).into(bottomModelBinding.iconGameImage)
        if (game.deck == null){
            bottomModelBinding.deckText.text = context?.resources?.getString(R.string.description_not_informed)
            bottomModelBinding.deckText.textSize = 12F

        } else{
            bottomModelBinding.deckText.text = game.deck
        }

        if (game.original_release_date == null){
            bottomModelBinding.originalReleaseDateText.text = context?.resources?.getString(R.string.release_date_not_informed)
            bottomModelBinding.originalReleaseDateText.textSize = 12F
        } else{
            bottomModelBinding.originalReleaseDateText.text = game.original_release_date
        }
        bottomModelBinding.platformsText.text = game.platforms[0].name
        bottomModelBinding.dragImageView.setOnClickListener {
            dialog.dismiss ()
        }
        bottomModelBinding.favoriteImageView.setOnClickListener {
            if (!game.name.isNullOrBlank() && !game.deck.isNullOrBlank()
                && !game.original_release_date.isNullOrBlank() && !game.platforms[0].name.isNullOrBlank()
                && !game.image.screen_large_url.isNullOrBlank()) {
                val favoriteGame = FavoriteGame(
                    name = game.name,
                    deck = game.deck,
                    original_release_date = game.original_release_date,
                    platforms = game.platforms[0].name,
                    image = game.image.screen_large_url)

                favoriteViewModel.addGame(favoriteGame)
                bottomModelBinding.favoriteImageView.setImageResource(R.drawable.ic_baseline_favorite_24)
                Toast.makeText(context, context?.resources?.getString(R.string.it_is_among_the_favourites), Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context, context?.resources?.getString(R.string.there_has_been_missing), Toast.LENGTH_LONG).show()
            }
        }
        dialog.show ()
    }
}