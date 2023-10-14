package com.example.banqumisrgraduation.presentation.ui.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.banqumisrgraduation.presentation.ui.activity.MainActivity
import com.example.banqumisrgraduation.R
import com.example.banqumisrgraduation.databinding.FragmentLoginBinding
import com.example.banqumisrgraduation.presentation.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    companion object {
        var userId: Int? = 0
        var userName: String? = null
        var login_email: String? = null
        var token: String? = null
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (binding.etEmail.text.isEmpty()) {
                binding.etEmail.setError("Required Field")
            } else if (binding.etPassword.text.isEmpty()) {
                binding.etPassword.setError("Required Field")
            } else {
               
                // Show Progress Bar
                binding.progressBar.visibility = View.VISIBLE
                // Disable user interactions
                requireActivity().getWindow()
                    .setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                val login = viewModel.login(email, password)
                login.observe(viewLifecycleOwner) {
                    if (it?.userId==-1) {
                        binding.progressBar.visibility = View.INVISIBLE
                        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Toast.makeText(requireContext(), "There is Error", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        userId=login.value?.userId
                        userName = login.value?.name
                        login_email = login.value?.email
                        binding.progressBar.visibility = View.INVISIBLE
                        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        val intent = Intent(requireContext(),MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }
                }

                }
            }

            binding.tvRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }
