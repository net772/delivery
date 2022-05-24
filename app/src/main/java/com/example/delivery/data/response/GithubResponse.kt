package com.example.delivery.data.response

import android.os.Parcelable
import com.example.delivery.data.entity.UserLikeEntity
import kotlinx.parcelize.Parcelize

data class GithubResponse(
    val total_count: Int,
    val incomplete_result: Boolean,
    val items: List<GithubData>
)

@Parcelize
data class GithubData(
    val id: Long,
    val avatar_url: String,
    val login: String,
    val html_url: String
): Parcelable {

    fun toEntity() = UserLikeEntity(
        id = id,
        avatar_url = avatar_url,
        login = login,
        html_url = html_url,
        state = false

    )
}


