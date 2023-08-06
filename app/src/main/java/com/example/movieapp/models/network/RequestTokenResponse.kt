package com.example.movieapp.models.network

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class RequestTokenResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("expires_at")
	val expiresAt: String? = null,

	@field:SerializedName("request_token")
	val requestToken: String? = null

) : Parcelable
