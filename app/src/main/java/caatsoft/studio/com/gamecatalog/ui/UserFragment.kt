package caatsoft.studio.com.gamecatalog.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import caatsoft.studio.com.gamecatalog.R
import caatsoft.studio.com.gamecatalog.adapter.GameAdapter
import caatsoft.studio.com.gamecatalog.auth.LoginActivity
import caatsoft.studio.com.gamecatalog.databinding.FragmentUserBinding
import caatsoft.studio.com.gamecatalog.viewmodel.UserViewModel
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class UserFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModels()
    private var _binding: FragmentUserBinding? = null

    private val binding get() = _binding!!
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mAuth = FirebaseAuth.getInstance()


        _binding = FragmentUserBinding.inflate(inflater, container, false)

        userViewModel.userName.observe(viewLifecycleOwner, { name->
            _binding?.nameTextview?.text = name
        })

        userViewModel.urlImage.observe(viewLifecycleOwner, { image->
            Glide.with(requireContext()).load(image).into(_binding?.userImageView!!)

        })

        userViewModel.getDataUser()

        _binding?.logoutTextView?.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}