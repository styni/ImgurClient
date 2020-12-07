package ru.spider.imgurclient.view.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava2.cachedIn
import io.reactivex.Flowable
import ru.spider.imgurclient.di.App
import ru.spider.imgurclient.model.ImageResponseData
import ru.spider.imgurclient.view.dataSource.GalleryImagesRepository
import javax.inject.Inject

class GalleryViewModel : ViewModel() {

    @Inject
    lateinit var repository: GalleryImagesRepository

    init {
        App.instance.component?.inject(this)
    }

    fun getImages(): Flowable<PagingData<ImageResponseData.ImageItem>> {
        return repository.getImages()
            .cachedIn(viewModelScope)
    }
}