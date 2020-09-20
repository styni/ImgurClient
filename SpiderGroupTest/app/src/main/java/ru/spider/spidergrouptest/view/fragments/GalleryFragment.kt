package ru.spider.spidergrouptest.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import io.reactivex.disposables.CompositeDisposable
import ru.spider.spidergrouptest.R
import ru.spider.spidergrouptest.databinding.FragmentGalleryBinding
import ru.spider.spidergrouptest.view.adapters.GalleryAdapter
import ru.spider.spidergrouptest.view.viewModels.GalleryViewModel

class GalleryFragment : BaseFragment() {

    private var binding: FragmentGalleryBinding? = null
    private val disposable = CompositeDisposable()
    private lateinit var adapter: GalleryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        binding = FragmentGalleryBinding.bind(view)
        setToolbar(R.string.gallery, false, binding?.root!!)

        binding?.viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = GalleryAdapter {
            val bundle = bundleOf(
                KEY_AUTHOR to it.accountUrl,
                KEY_TITLE to it.title,
                KEY_URL to it.link,
                KEY_ID to it.id
            )
            NavHostFragment.findNavController(this).navigate(R.id.imageDetailFragment, bundle)
        }
        binding?.recyclerView?.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        binding?.recyclerView?.adapter = adapter

        disposable.add(binding?.viewModel?.getImages()?.subscribe {
            adapter.submitData(lifecycle, it)
        }!!)
    }

    override fun onDestroyView() {
        disposable.dispose()
        super.onDestroyView()
    }

    companion object {
        const val KEY_AUTHOR = "author"
        const val KEY_URL = "imageUrl"
        const val KEY_TITLE = "title"
        const val KEY_ID = "id"
    }
}