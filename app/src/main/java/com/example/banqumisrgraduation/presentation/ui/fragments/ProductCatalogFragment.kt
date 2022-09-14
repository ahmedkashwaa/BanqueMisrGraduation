package com.example.banqumisrgraduation.presentation.ui.fragments

import ProductCatalogAdapter
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView.BufferType
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.banqumisrgraduation.R
import com.example.banqumisrgraduation.databinding.FragmentProductcatalogBinding
import com.example.banqumisrgraduation.presentation.viewmodel.ProductCatalogViewModel


class ProductCatalogFragment : Fragment() {

    private var _binding: FragmentProductcatalogBinding? = null
    private val binding get() = _binding!!
    var adapter: ProductCatalogAdapter? = null
    private val viewModel: ProductCatalogViewModel by lazy {
        ViewModelProvider(this).get(ProductCatalogViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProductcatalogBinding.inflate(layoutInflater)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeColor()
        adapter = ProductCatalogAdapter()
        viewModel.products.observe(viewLifecycleOwner) {
            adapter?.submitList(it)
        }
        binding.rv.adapter = adapter
    }

    private fun changeColor(){
        val span_text = getString(R.string.it_s_great_day_for_coffee)
        val text = SpannableStringBuilder(span_text)
        val text2 = "Day"
        val index = span_text.indexOf(text2)
        text.setSpan(ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.Text)),index,span_text.length,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.textView.setText(text, BufferType.SPANNABLE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}