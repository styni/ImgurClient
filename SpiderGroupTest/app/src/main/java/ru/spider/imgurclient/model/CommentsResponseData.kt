package ru.spider.imgurclient.model

import com.google.gson.annotations.SerializedName

data class CommentsResponseData(
    @SerializedName("data")
    val data: List<CommentItem>? = null,

    @SerializedName("success")
    val success: Boolean? = null,

    @SerializedName("status")
    val status: Int? = null
)

    data class ChildrenItem(
        @SerializedName("author")
        val author: String? = null,

        @SerializedName("points")
        val points: Int? = null,

        @SerializedName("datetime")
        val datetime: Int? = null,

        @SerializedName("deleted")
        val deleted: Boolean? = null,

        @SerializedName("parent_id")
        val parentId: Int? = null,

        @SerializedName("comment")
        val comment: String? = null,

        @SerializedName("on_album")
        val onAlbum: Boolean? = null,

        @SerializedName("id")
        val id: Int? = null,

        @SerializedName("image_id")
        val imageId: String? = null,

        @SerializedName("author_id")
        val authorId: Int? = null
    )

data class CommentItem(

    @SerializedName("downs")
    val downs: Int? = null,

    @SerializedName("author")
    val author: String? = null,

    @SerializedName("points")
    val points: Int? = null,

    @SerializedName("datetime")
    val datetime: Int? = null,

    @SerializedName("deleted")
    val deleted: Boolean? = null,

    @SerializedName("children")
    val children: List<ChildrenItem?>? = null,

    @SerializedName("parent_id")
    val parentId: Int? = null,

    @SerializedName("comment")
    val comment: String? = null,

    @SerializedName("on_album")
    val onAlbum: Boolean? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("image_id")
    val imageId: String? = null,

    @SerializedName("author_id")
    val authorId: Int? = null
)
