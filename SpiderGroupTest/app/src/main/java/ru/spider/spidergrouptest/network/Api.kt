package ru.spider.spidergrouptest.network

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import ru.spider.spidergrouptest.model.CommentsResponseData
import ru.spider.spidergrouptest.model.ImageResponseData

interface Api {

    /**
     * Загрузка фото со страницы
     *
     * @param page страница
     **/
    @GET("gallery/top/viral/day/{page}?showViral=true&mature=true&album_previews=true")
    @Headers("Authorization: Client-ID $CLIENT_ID")
    fun getGalleryItems(@Path(value = "page", encoded = true) page: Int) : Single<ImageResponseData>

    /**
     * Загрузка комментариев
     *
     * @param galleryHash id изображения
     **/
    @GET("gallery/{galleryHash}/comments/best")
    @Headers("Authorization: Client-ID $CLIENT_ID")
    fun getCommentsList(@Path(value="galleryHash", encoded = true) galleryHash: String) : Observable<CommentsResponseData>

    companion object {
        const val BASE_URL      = "https://api.imgur.com/3/"
        const val CLIENT_ID     = "546c25a59c58ad7"
    }
}