package tk.skeptick.vk.apiclient.methods.photos

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.methods.GetUploadServerResponse

@Serializable
data class PhotosUploadServerResponse(
    @SerialName("upload_url") override val uploadUrl: String,
    @SerialName("album_id") val albumId: Int,
    @SerialName("user_id") val userId: Int? = null,
    @SerialName("group_id") val groupId: Int? = null
) : GetUploadServerResponse

@Serializable
data class Tag(
    @SerialName("id") val id: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("placer_id") val placerId: Int,
    @SerialName("tagged_name") val taggedName: String,
    @SerialName("date") val date: Int,
    @SerialName("x") val x: Double,
    @SerialName("y") val y: Double,
    @SerialName("x2") val x2: Double,
    @SerialName("y2") val y2: Double,
    @SerialName("viewed") val isViewed: BooleanInt) {

    val isTextTag: Boolean get() = userId == 0

}

@Serializable
data class SaveOwnerPhotoResponse(
    @SerialName("photo_hash") val photoHash: String,
    @SerialName("photo_src") val photoSrc: String,
    @SerialName("photo_src_big") val photoSrcBig: String,
    @SerialName("photo_src_small") val photoSrcSmall: String,
    @SerialName("saved") val isSaved: BooleanInt,
    @SerialName("post_id") val postId: Int)