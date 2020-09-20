package ru.spider.spidergrouptest.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.reactivex.disposables.CompositeDisposable
import ru.spider.spidergrouptest.R
import ru.spider.spidergrouptest.databinding.FragmentImageDetailBinding
import ru.spider.spidergrouptest.model.ImageResponseData
import ru.spider.spidergrouptest.view.adapters.CommentsAdapter
import ru.spider.spidergrouptest.view.viewModels.ImageDetailViewModel

class ImageDetailFragment: BaseFragment() {

    private var binding: FragmentImageDetailBinding? = null
    private var adapter: CommentsAdapter? = null
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image_detail, container, false)
        binding = FragmentImageDetailBinding.bind(view)

        val item = ImageResponseData.ImageItem(
            id = arguments?.getString(GalleryFragment.KEY_ID),
            link = arguments?.getString(GalleryFragment.KEY_URL),
            accountUrl = arguments?.getString(GalleryFragment.KEY_AUTHOR),
            title = arguments?.getString(GalleryFragment.KEY_TITLE)
            )

        binding?.viewModel = ViewModelProvider(this, ImageDetailViewModel.Factory(item)).get(ImageDetailViewModel::class.java)
        setToolbar(R.string.info, true, binding?.root!!)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.comments?.layoutManager = LinearLayoutManager(this.context)

        binding?.viewModel?.getComments()?.subscribe {
            it?.let { binding?.comments?.adapter = CommentsAdapter(it) }
        }?.let { disposable.add(it) }

        val image = binding?.viewModel?.image

        binding?.title?.text = image?.title ?: ""
        binding?.userName?.text = image?.accountUrl ?: "USER NOT FOUND"

        binding?.image?.let {
            Glide.with(it)
                .load(image?.link)
                .into(it)
        }
    }

    override fun onDestroyView() {
        disposable.dispose()
        super.onDestroyView()
    }
}