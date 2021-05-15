package com.lukitor.projectandroidjetpackpro1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lukitor.projectandroidjetpackpro1.databinding.FragmentDataBinding
import com.lukitor.projectandroidjetpackpro1.viewmodel.ViewModelFactory

class DataFragment : Fragment() {
    private var tipe: String= ""
    private  lateinit var fragmentDataBinding: FragmentDataBinding
    private lateinit var viewModel: DataViewModel
    private lateinit var dataadapter: DataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {tipe = it.getString("tipe").toString()}
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity!=null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[DataViewModel::class.java]
            fragmentDataBinding.progressBar.visibility = View.VISIBLE
            dataadapter = DataAdapter()
            viewModel.getAllData()
            if (tipe == "All"){
                viewModel.getAll().observe(this, { courses ->
                    fragmentDataBinding.progressBar.visibility = View.GONE
                    dataadapter.setMovie(courses)
                    dataadapter.notifyDataSetChanged()
                })
            }
            else if (tipe == "Movie"){
                viewModel.getMovie().observe(this, { courses ->
                    fragmentDataBinding.progressBar.visibility = View.GONE
                    dataadapter.setMovie(courses)
                    dataadapter.notifyDataSetChanged()
                })
            }
            else{
                viewModel.getTV().observe(this, { courses ->
                    fragmentDataBinding.progressBar.visibility = View.GONE
                    dataadapter.setMovie(courses)
                    dataadapter.notifyDataSetChanged()
                })
            }
            with(fragmentDataBinding.rvFollowing) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = dataadapter
            }
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        fragmentDataBinding = FragmentDataBinding.inflate(layoutInflater, container, false)
        return fragmentDataBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(tipe: String) = DataFragment().apply {arguments = Bundle().apply {putString("tipe", tipe)}}
    }
}