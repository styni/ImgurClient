package ru.spider.imgurclient.view.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.Observable
import ru.spider.imgurclient.di.App
import ru.spider.imgurclient.model.CommentItem
import ru.spider.imgurclient.model.ImageResponseData
import ru.spider.imgurclient.view.dataSource.GalleryImagesRepository
import javax.inject.Inject

class ImageDetailViewModel(val image: ImageResponseData.ImageItem): ViewModel() {

    @Inject
    lateinit var repository: GalleryImagesRepository

    init {
        App.instance.component?.inject(this)
    }

    fun getComments(): Observable<List<CommentItem>?>? {
        return image.id?.let { repository.getComments(it) }
    }

    class Factory(val item: ImageResponseData.ImageItem): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ImageDetailViewModel(item) as T
        }
    }
}