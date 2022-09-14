package com.example.banqumisrgraduation.presentation.ui.fragments

import CartAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.banqumisrgraduation.presentation.ui.activity.MainActivity
import com.example.banqumisrgraduation.R
import com.example.banqumisrgraduation.data.database.OrdersDatabase
import com.example.banqumisrgraduation.data.database.asEntities
import com.example.banqumisrgraduation.databinding.FragmentCartBinding
import com.example.banqumisrgraduation.presentation.ui.activity.CoffeBuyActivity
import com.example.banqumisrgraduation.presentation.viewmodel.CartViewModel

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    var adapter: CartAdapter? = null

    private val viewModel: CartViewModel by lazy {
        ViewModelProvider(this).get(CartViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val database = OrdersDatabase.getInstance(requireContext())
        binding.lifecycleOwner=this
        binding.viewModel = viewModel
        binding.cartBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        adapter = CartAdapter {
            viewModel.changePrice()
        }
        viewModel.products.observe(viewLifecycleOwner){
            adapter?.submitList(it)

        }

        binding.cartItemsRv.adapter = adapter

        binding.checkoutBtn.setOnClickListener {
            database.dao.insertAll(adapter?.currentList!!.asEntities(LoginFragment.userId!!))
            if (adapter?.currentList!!.size!=0){
                Toast.makeText(requireContext(), "Checked out Successfuly", Toast.LENGTH_SHORT).show()
            }
            CoffeBuyActivity.cartItems.clear()

            adapter?.notifyDataSetChanged()
            viewModel.changePrice()
            MainActivity.refreshBadge(activity as MainActivity)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        swipeToDelete()
        adapter?.notifyDataSetChanged()
        viewModel.changePrice()
    }

    private fun swipeToDelete() {
        val callback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                showDeleteDialog(position)
            }
        }
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.cartItemsRv)
    }

    private fun showDeleteDialog(position: Int) {
        val builder = AlertDialog.Builder(requireContext(),R.style.AlertDialogCustom)
        builder
            .setTitle(R.string.delete_dialog_title)
            .setMessage(R.string.delete_dialog_message)
            .setPositiveButton(R.string.delete_dialog_positive) { dialog, which ->
                // To notify array list and adapter that some data deleted
                CoffeBuyActivity.cartItems.removeAt(position)
                viewModel.changePrice()
                adapter!!.notifyDataSetChanged()
                MainActivity.refreshBadge(activity as MainActivity)

            }
            .setNegativeButton(R.string.delete_dialog_negative) { dialog, which ->
                adapter!!.notifyItemChanged(position)
            } // avoid problem by clicking in any place outside the dialog or back button
            .setCancelable(false)
            .show()
    }


}