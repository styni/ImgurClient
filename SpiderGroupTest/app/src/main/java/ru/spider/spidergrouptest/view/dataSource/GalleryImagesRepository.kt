package ru.spider.spidergrouptest.view.dataSource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ru.spider.spidergrouptest.model.CommentItem
import ru.spider.spidergrouptest.model.ImageResponseData
import javax.inject.Inject

class GalleryImagesRepository @Inject constructor(var pagingSource: GalleryImagesPagingSource, var imageCommentsSource: ImageCommentsSource) {

    /**
     * Загрузка галереии пагинацией
     **/
    fun getImages(): Flowable<PagingData<ImageResponseData.ImageItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40),
            pagingSourceFactory = { pagingSource }
        ).flowable
    }

    /**
     * Загрузка комментариев к фото
     **/
    fun getComments(galleryHash: String): Observable<List<CommentItem>?> {
        return imageCommentsSource.getComments(galleryHash)
    }
}