package caatsoft.studio.com.gamecatalog.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import caatsoft.studio.com.gamecatalog.R
import caatsoft.studio.com.gamecatalog.adapter.GameAdapter
import caatsoft.studio.com.gamecatalog.auth.LoginActivity
import caatsoft.studio.com.gamecatalog.databinding.FragmentUserBinding
import caatsoft.studio.com.gamecatalog.viewmodel.UserViewModel
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModel()
    private var _binding: FragmentUserBinding? = null
    private lateinit var alertDialog: AlertDialog

    private val binding get() = _binding!!
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mAuth = FirebaseAuth.getInstance()


        _binding = FragmentUserBinding.inflate(inflater, container, false)

        userViewModel.isLoading.observe(viewLifecycleOwner,
            { isLoading ->
                if (isLoading != null) {
                    if (isLoading) {
                        startLoading()
                    } else{
                        dismissDialog()
                    }
                }
            })

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

    fun startLoading() {
        val builder = AlertDialog.Builder(this.context)
        val inflater = requireActivity().layoutInflater
        builder.setView(inflater.inflate(R.layout.popup_loading, null))
        builder.setCancelable(true)
        alertDialog = builder.create()
        alertDialog.show()
    }

    fun dismissDialog() {
        alertDialog.dismiss()
    }

}