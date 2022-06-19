package com.wentao.githubapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wentao.githubapp.databinding.FragmentHomeBinding
import com.wentao.githubapp.network.NetworkManager
import com.wentao.githubapp.network.Resource
import com.wentao.githubapp.ui.BaseFragment
import com.wentao.githubapp.ui.home.model.HomeRepository
import com.wentao.githubapp.ui.home.model.ItemsViewModel

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView = binding.recyclerViewHome
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = HomeEventAdapter()
        recyclerView.adapter = adapter
        homeViewModel.getEvents()
            .observe(viewLifecycleOwner) { response ->
                if (response.status == Resource.Status.SUCCESS) {
                    response.data?.let { it ->
                        val list: ArrayList<ItemsViewModel> = arrayListOf()
                        for (event in it) {
                            event.actor?.displayLogin?.let { displayLogin ->
                                var itemsViewModel = event.payload?.description?.let { it1 ->
                                    event.actor?.avatarUrl?.let { it2 ->
                                        ItemsViewModel(
                                            displayLogin,
                                            it1,
                                            it2
                                        )
                                    }
                                }
                                if (itemsViewModel != null) {
                                    list.add(itemsViewModel)
                                }
                            }
                        }
                        adapter.setData(list)
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