package com.lukitor.projectandroidjetpackpro1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukitor.projectandroidjetpackpro1.databinding.FragmentFavoriteBinding
import com.lukitor.projectandroidjetpackpro1.viewmodel.ViewModelFactory

class FavoriteFragment : Fragment() {
    private var tipe: String= ""
    private  lateinit var fragmentDataBinding: FragmentFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {tipe = it.getString("tipe").toString()}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            val adapter = FavoriteAdapter()
            fragmentDataBinding.progressBar.visibility = View.VISIBLE
            viewModel.getBookmarks(tipe).observe(this, { courses ->
                fragmentDataBinding.progressBar.visibility = View.GONE
                adapter.setFavorite(courses)
                adapter.notifyDataSetChanged()
            })
            fragmentDataBinding.rvFollowing.layoutManager = LinearLayoutManager(context)
            fragmentDataBinding.rvFollowing.setHasFixedSize(true)
            fragmentDataBinding.rvFollowing.adapter = adapter
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        fragmentDataBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentDataBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(tipe: String) = FavoriteFragment().apply {arguments = Bundle().apply {putString("tipe", tipe)}}
    }
}