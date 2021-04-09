package com.marvelapp.br.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.marvelapp.br.R
import com.marvelapp.br.adapter.PersonagemPagedAdapter
import com.marvelapp.br.viewmodel.PersonagemViewModel
import kotlinx.android.synthetic.main.fragment_personagens_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonagensListFragment : Fragment() {

    private var navControler : NavController? = null
    private val viewModel: PersonagemViewModel by viewModel()
    private val personageAdapter: PersonagemPagedAdapter = PersonagemPagedAdapter(::navigateToDetail, ::onLoadComplete)
    private lateinit var shimmer: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = getString(R.string.personagens_fragment_title)
        setHasOptionsMenu(false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return inflater.inflate(R.layout.fragment_personagens_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shimmer = view.findViewById(R.id.placeholder)
        navControler = Navigation.findNavController(requireActivity(),R.id.nav_host)
        recycler_personagens.layoutManager = LinearLayoutManager(requireContext())
        getObservers()
    }

    override fun onPause() {
        super.onPause()
        shimmer.visibility = View.GONE
    }

    private fun getObservers() {
        viewModel.getPersonagens().observe(viewLifecycleOwner, Observer {
            personageAdapter.submitList(it)
            recycler_personagens.adapter = personageAdapter
        })
    }

    fun navigateToDetail(id: Int) {
        var bundle = Bundle()
        bundle.putInt("id",id)
        navControler?.navigate(R.id.action_personagensListFragment_to_personagemDetalheFragment,bundle)
    }

    private fun  onLoadComplete() {
        shimmer.visibility = GONE
    }
}