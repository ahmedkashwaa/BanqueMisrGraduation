package com.example.banqumisrgraduation.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.banqumisrgraduation.R
import com.example.banqumisrgraduation.data.model.RegisterBody
import com.example.banqumisrgraduation.data.network.service
import com.example.banqumisrgraduation.databinding.FragmentCartBinding
import com.example.banqumisrgraduation.databinding.FragmentRegisterBinding
import com.example.banqumisrgraduation.presentation.viewmodel.RegisterViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterFragment : Fragment() {
    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvSignIn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnBackToLogin.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnRegister.setOnClickListener {
            val userName = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (binding.etEmail.text.isEmpty()) {
                binding.etEmail.setError("Required Field")
            } else if (binding.etPassword.text.isEmpty()) {
                binding.etPassword.setError("Required Field")
            } else if (binding.etUsername.text.isEmpty()) {
                binding.etUsername.setError("Required Field")
            } else {

                // Show Progress Bar
                binding.progressBar.visibility = View.VISIBLE
                // Disable user interactions
                requireActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                val register = viewModel.register(userName, email, password)
                register.observe(viewLifecycleOwner) {
                    if (it==2) {
                        Toast.makeText(requireContext(), "Already Registered", Toast.LENGTH_SHORT).show()
                        binding.progressBar.visibility = View.INVISIBLE
                        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                    else if (it==1){
                        binding.progressBar.visibility = View.INVISIBLE
                        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        val builder = AlertDialog.Builder(requireContext(),R.style.AlertDialogCustom)
                        builder
                            .setTitle("Successfully Registered")
                            .setMessage("Going To Login Page")
                            .setPositiveButton("Go") { dialog, which ->
                                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                            }
                            .setCancelable(false)
                            .show()
                    } else {
                        Toast.makeText(requireContext(), "Connection Error", Toast.LENGTH_SHORT)
                            .show()
                        binding.progressBar.visibility = View.INVISIBLE
                        requireActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                }
            }
        }
    }
}