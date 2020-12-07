package ru.spider.imgurclient.view.dataSource

import androidx.paging.rxjava2.RxPagingSource
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.spider.imgurclient.model.ImageResponseData
import ru.spider.imgurclient.network.Api
import javax.inject.Inject

/**
 * Источник фото для пагинации
 **/
class GalleryImagesPagingSource @Inject constructor(var api: Api) : RxPagingSource<Int, ImageResponseData.ImageItem>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ImageResponseData.ImageItem>> {
        val page = params.key ?: 1

        return api.getGalleryItems(page)
            .subscribeOn(Schedulers.io())
            .map { toLoadResult(it.imageList, page) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: List<ImageResponseData.ImageItem>, position: Int): LoadResult<Int, ImageResponseData.ImageItem> {
        return LoadResult.Page(
            data = data,
            prevKey = if (position == 1) null else position - 1,
            nextKey = position + 1
        )
    }

}