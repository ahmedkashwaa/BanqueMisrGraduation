package com.example.banqumisrgraduation.presentation.ui.fragments

import ProfileHistoryAdapter
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.banqumisrgraduation.data.database.OrdersDatabase
import com.example.banqumisrgraduation.data.database.asProducts
import com.example.banqumisrgraduation.data.model.Products
import com.example.banqumisrgraduation.databinding.FragmentProfileBinding
import com.example.banqumisrgraduation.presentation.ui.activity.AuthActivity

class ProfileFragment : Fragment() {

        var userName : String? = LoginFragment.userName
        var login_email : String? = LoginFragment.login_email
        var adapter : ProfileHistoryAdapter? = null
    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = OrdersDatabase.getInstance(requireContext())
        adapter = ProfileHistoryAdapter()
        adapter?.submitList(database.dao.getAll(LoginFragment.userId!!).asProducts())

        binding.rvOrder.adapter = adapter


        binding.logoutBtn.setOnClickListener {
            startActivity(Intent(requireContext(), AuthActivity::class.java))
            requireActivity().finish()
        }
        binding.profileUsernameTv.text = userName
        binding.profileEmailTv.text = login_email

        if (adapter?.currentList!!.size!=0){
            binding.remove.visibility = View.VISIBLE
        }

        binding.remove.setOnClickListener {

            adapter?.submitList(listOf<Products>())
            database.dao.delete(LoginFragment.userId!!)
            adapter?.notifyDataSetChanged()
            binding.remove.visibility = View.INVISIBLE
            Toast.makeText(requireContext(), "History Cleared", Toast.LENGTH_SHORT).show()
        }

        binding.profileEmailTv.setOnClickListener {
            Toast.makeText(requireContext(), database.dao.getAll(LoginFragment.userId!!).toString(), Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}