package caatsoft.studio.com.gamecatalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import caatsoft.studio.com.gamecatalog.databinding.FragmentFavoriteBinding
import caatsoft.studio.com.gamecatalog.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        favoriteViewModel.textFavorite.observe(viewLifecycleOwner, {
            _binding?.textFavorite?.text = it.toString()
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}