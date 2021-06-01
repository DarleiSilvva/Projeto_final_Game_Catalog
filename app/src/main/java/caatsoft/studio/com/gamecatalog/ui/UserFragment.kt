package caatsoft.studio.com.gamecatalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import caatsoft.studio.com.gamecatalog.databinding.FragmentUserBinding
import caatsoft.studio.com.gamecatalog.viewmodel.UserViewModel

class UserFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModels()
    private var _binding: FragmentUserBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserBinding.inflate(inflater, container, false)

        userViewModel.textUser.observe(viewLifecycleOwner, {
            _binding?.textUser?.text = it.toString()
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}