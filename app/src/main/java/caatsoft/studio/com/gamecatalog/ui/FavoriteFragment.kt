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
import caatsoft.studio.com.gamecatalog.databinding.BottomModelBinding
import caatsoft.studio.com.gamecatalog.databinding.FragmentFavoriteBinding
import caatsoft.studio.com.gamecatalog.network.model.FavoriteGame
import caatsoft.studio.com.gamecatalog.repository.FavoriteRepositoryImpl
import caatsoft.studio.com.gamecatalog.viewmodel.FavoriteViewModel
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class FavoriteFragment : Fragment(), FavoriteGameAdapter.GameClickListener, KoinComponent {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null
    private lateinit var favoriteGameAdapter: FavoriteGameAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        favoriteViewModel.games.observe(viewLifecycleOwner, { gamesList ->
            val mLayoutManager = GridLayoutManager( this.context, 2)
            _binding?.gameRecyclerview?.layoutManager = mLayoutManager;
            favoriteGameAdapter = FavoriteGameAdapter(gamesList, this)
            _binding?.gameRecyclerview?.adapter = favoriteGameAdapter
        })
        favoriteViewModel.getGames()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onGameClicked(favoriteGame: FavoriteGame) {
        val bottomModelBinding: BottomModelBinding = BottomModelBinding.inflate(layoutInflater)

        val dialog = BottomSheetDialog (requireContext())
        dialog.setContentView (bottomModelBinding.root)
        bottomModelBinding.nameText.text = favoriteGame.name
        Glide.with(requireContext()).load(favoriteGame.image).into(bottomModelBinding.iconGameImage)
        if (favoriteGame.deck == null){
            bottomModelBinding.deckText.text = context?.resources?.getString(R.string.description_not_informed)
            bottomModelBinding.deckText.textSize = 12F

        } else{
            bottomModelBinding.deckText.text = favoriteGame.deck
        }

        if (favoriteGame.original_release_date == null){
            bottomModelBinding.originalReleaseDateText.text = context?.resources?.getString(R.string.release_date_not_informed)
            bottomModelBinding.originalReleaseDateText.textSize = 12F
        } else{
            bottomModelBinding.originalReleaseDateText.text = favoriteGame.original_release_date
        }
        bottomModelBinding.platformsText.text = favoriteGame.platforms
        bottomModelBinding.dragImageView.setOnClickListener {
            dialog.dismiss ()
        }
        bottomModelBinding.favoriteImageView.setImageResource(R.drawable.ic_baseline_favorite_24)

        bottomModelBinding.favoriteImageView.setOnClickListener {
            if (!favoriteGame.name.isNullOrBlank() && !favoriteGame.deck.isNullOrBlank()
                && !favoriteGame.original_release_date.isNullOrBlank() && !favoriteGame.platforms.isNullOrBlank()
                && !favoriteGame.image.isNullOrBlank()) {
                favoriteViewModel.removeGame(favoriteGame)
                bottomModelBinding.favoriteImageView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                Toast.makeText(context, context?.resources?.getString(R.string.it_is_not_among_the_favourites), Toast.LENGTH_LONG).show()
            }
        }
        dialog.show ()
    }
}