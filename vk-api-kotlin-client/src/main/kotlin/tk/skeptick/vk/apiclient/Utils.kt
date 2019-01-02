@file:Suppress("UNUSED")

package tk.skeptick.vk.apiclient

const val CHAT_ID_ADDENDUM = 2000000000

inline val Int.peerIdToCommunityId: Int get() = -this
inline val Int.peerIdToChatId: Int get() = this - CHAT_ID_ADDENDUM

inline val Int.communityIdToPeerId: Int get() = -this
inline val Int.chatIdToPeerId: Int get() = this + CHAT_ID_ADDENDUM

inline val Int.isCommunityPeerId: Boolean get() = this < 0
inline val Int.isChatPeerId: Boolean get() = this > CHAT_ID_ADDENDUM
inline val Int.isEmailPeerId: Boolean get() = this < 0 && (-this).isChatPeerId
inline val Int.isUserPeerId: Boolean get() = !isCommunityPeerId && !isChatPeerId && !isEmailPeerId