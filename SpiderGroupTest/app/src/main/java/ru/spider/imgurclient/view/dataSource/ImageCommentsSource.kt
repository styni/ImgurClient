package ru.spider.imgurclient.view.dataSource

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.spider.imgurclient.model.CommentItem
import ru.spider.imgurclient.network.Api
import javax.inject.Inject

/**
 * Источник комментариев
 **/
class ImageCommentsSource @Inject constructor(var api: Api) {

    fun getComments(galleryHash: String): Observable<List<CommentItem>?> {
        return api.getCommentsList(galleryHash)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.data }
            .onErrorReturn { null }
    }

}