package tk.skeptick.vk.apiclient.domain.models

import kotlinx.serialization.*
import tk.skeptick.vk.apiclient.BooleanInt
import tk.skeptick.vk.apiclient.EnumIntSerializer
import tk.skeptick.vk.apiclient.SerializableEnum
import tk.skeptick.vk.apiclient.domain.AttachmentType
import tk.skeptick.vk.apiclient.domain.WallAttachment

@Serializable
data class Page(
    @SerialName("id") override val id: Int,
    @SerialName("group_id") val groupId: Int,
    @SerialName("title") val title: String,
    @SerialName("edited") val editedDate: Int,
    @SerialName("created") val createdDate: Int,
    @SerialName("views") val viewsCount: Int,
    @SerialName("view_url") val viewUrl: String,
    @SerialName("who_can_view") val whoCanView: PrivacyType,
    @SerialName("who_can_edit") val whoCanEdit: PrivacyType,
    @SerialName("source") val source: String? = null,
    @SerialName("html") val html: String? = null,
    @SerialName("parent") val parentPageTitle: String? = null,
    @SerialName("parent2") val parent2PageTitle: String? = null,
    @SerialName("creator_id") val creatorId: Int? = null,
    @SerialName("editor_id") val editorId: Int? = null,
    @SerialName("current_user_can_edit") val isCurrentUserCanEdit: BooleanInt? = null,
    @SerialName("current_user_can_edit_access") val isCurrentUserCanEditAccess: BooleanInt? = null
) : WallAttachment {

    override val typeAttachment: AttachmentType get() = AttachmentType.PAGE

    override val ownerId: Int get() = -groupId

    override val accessKey: String? = null

    @Serializable(with = PrivacyType.Companion::class)
    enum class PrivacyType(override val value: Int) : SerializableEnum<Int> {
        ALL_USERS(2),
        COMMUNITY_MEMBERS(1),
        COMMUNITY_MANAGERS(0);

        companion object : EnumIntSerializer<PrivacyType>(PrivacyType::class)
    }

}