@file:Suppress("UNUSED")

package tk.skeptick.vk.apiclient

import com.github.kittinunf.result.Result

const val CHAT_ID_ADDENDUM = 2000000000

inline val Int.peerIdToCommunityId: Int get() = -this
inline val Int.peerIdToChatId: Int get() = this - CHAT_ID_ADDENDUM

inline val Int.communityIdToPeerId: Int get() = -this
inline val Int.chatIdToPeerId: Int get() = this + CHAT_ID_ADDENDUM

inline val Int.isCommunityPeerId: Boolean get() = this < 0
inline val Int.isChatPeerId: Boolean get() = this > CHAT_ID_ADDENDUM
inline val Int.isEmailPeerId: Boolean get() = this < 0 && (-this).isChatPeerId
inline val Int.isUserPeerId: Boolean get() = !isCommunityPeerId && !isChatPeerId && !isEmailPeerId

//inline fun <R : Any, E : Exception> Result<R, E>.takeIfSuccessOr(
//    block: (E) -> R
//): R = when (this) {
//    is Result.Success -> value
//    is Result.Failure -> block(error)
//}