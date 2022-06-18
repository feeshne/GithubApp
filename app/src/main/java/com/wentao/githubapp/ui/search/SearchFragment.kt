package com.wentao.githubapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wentao.githubapp.databinding.FragmentSearchBinding
import com.wentao.githubapp.network.ApiService
import com.wentao.githubapp.network.NetworkManager
import com.wentao.githubapp.ui.BaseFragment
import com.wentao.githubapp.ui.profile.model.UserRepository
import com.wentao.githubapp.ui.search.model.ItemsViewModel
import com.wentao.githubapp.ui.search.model.RepoRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchFragment : BaseFragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView = binding.recyclerViewRepo
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = RepoSearchAdapter()
        recyclerView.adapter = adapter
        binding.buttonSearch.setOnClickListener {
            val key = binding.inputRepoKey.text.toString()
            key.isNotEmpty().let {
                RepoRepository(NetworkManager().getApiService()).getRepos(key)
                    .observe(viewLifecycleOwner) { response ->
                        response.data?.let {
                            val list: ArrayList<ItemsViewModel> = arrayListOf()
                            for (repo in it.items) {
                                repo.description?.let {
                                    val model = ItemsViewModel(repo.name, repo.description)
                                    list.add(model)
                                }
                            }
                            adapter.setData(list)
                        }
                    }
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}