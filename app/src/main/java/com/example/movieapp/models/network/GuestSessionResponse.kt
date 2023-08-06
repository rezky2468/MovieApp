package com.example.movieapp.models.network

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GuestSessionResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("expires_at")
	val expiresAt: String? = null,

	@field:SerializedName("guest_session_id")
	val guestSessionId: String? = null

) : Parcelable
