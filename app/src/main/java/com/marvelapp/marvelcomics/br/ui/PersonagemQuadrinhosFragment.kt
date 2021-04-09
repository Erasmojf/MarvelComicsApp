package com.marvelapp.br.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.marvelapp.br.adapter.PersonagemQuadrinhosAdapter
import com.marvelapp.br.viewmodel.PersonagemViewModel
import com.marvelapp.marvelcomics.br.R
import kotlinx.android.synthetic.main.fragment_personagem_detalhe.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonagemQuadrinhosFragment : Fragment() {

    private val viewModel: PersonagemViewModel by viewModel()
    private lateinit var adapter: PersonagemQuadrinhosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        return inflater.inflate(R.layout.fragment_personagem_detalhe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       recycler_comics.layoutManager = LinearLayoutManager(requireContext())

       viewModel.getComics(this.arguments?.getInt("id")!!)
        viewModel.personagemDetalheLiveData.observeForever {
            adapter = PersonagemQuadrinhosAdapter(it.data.results, ::openURL)
            recycler_comics.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        if (searchItem != null) {
            val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
            searchView.setOnCloseListener { true }

            val searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_src_text) as EditText
            searchPlate.hint = getString(R.string.search)
            val searchPlateView: View =
                searchView.findViewById(androidx.appcompat.R.id.search_plate)
            searchPlateView.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    android.R.color.transparent
                )
            )

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }
            })

            val searchManager =
                activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        }
        return super.onCreateOptionsMenu(menu,inflater)
    }

    private fun openURL(url: String) {
        startActivity(Intent(ACTION_VIEW, Uri.parse(url)))
    }
}
