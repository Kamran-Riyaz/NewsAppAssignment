package com.example.news_assignment.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class NewsResponseModel(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class NodesItem(

	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("image")
	val image: List<ImageItem?>? = null,

	@field:SerializedName("created")
	val created: Long? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("linked_title")
	val linkedTitle: String? = null,

	@field:SerializedName("node_id")
	val nodeId: String? = null,

	@field:SerializedName("changed")
	val changed: Long? = null
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("page_number")
	val pageNumber: Int? = null,

	@field:SerializedName("nodes")
	val nodes: List<NodesItem?>? = null,

	@field:SerializedName("total_records")
	val totalRecords: String? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("page_size")
	val pageSize: Int? = null
) : Parcelable

@Parcelize
data class ImageItem(

	@field:SerializedName("file_url")
	val fileUrl: String? = null,

	@field:SerializedName("filename")
	val filename: String? = null,

	@field:SerializedName("filemime")
	val filemime: String? = null
) : Parcelable
