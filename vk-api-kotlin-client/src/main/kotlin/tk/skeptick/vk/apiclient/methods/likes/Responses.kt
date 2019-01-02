package tk.skeptick.vk.apiclient.methods.likes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import tk.skeptick.vk.apiclient.BooleanInt

@Serializable
data class ChangeLikeResponse(
    @SerialName("likes") val likes: Int)

@Serializable
data class IsLikedResponse(
    @SerialName("liked") val isLiked: BooleanInt,
    @SerialName("copied") val isCopied: BooleanInt)