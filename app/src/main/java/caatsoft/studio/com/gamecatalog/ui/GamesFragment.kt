package caatsoft.studio.com.gamecatalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import caatsoft.studio.com.gamecatalog.adapter.GameAdapter
import caatsoft.studio.com.gamecatalog.databinding.FragmentGamesBinding
import caatsoft.studio.com.gamecatalog.viewmodel.GamesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GamesFragment : Fragment() {

    private var _binding: FragmentGamesBinding? = null
    private val gamesViewModel: GamesViewModel by viewModel()
    private lateinit var gameAdapter: GameAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGamesBinding.inflate(inflater, container, false)

        gamesViewModel.games.observe(viewLifecycleOwner, { gamesList ->
            val mLayoutManager = GridLayoutManager( this.context, 2)
            _binding?.gameRecyclerview?.layoutManager = mLayoutManager;
            gameAdapter = GameAdapter(this.requireContext(), gamesList)
            _binding?.gameRecyclerview?.adapter = gameAdapter
        })
        gamesViewModel.getFilteredGames()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}