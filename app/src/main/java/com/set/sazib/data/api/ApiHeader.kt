package com.set.sazib.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.set.sazib.di.VersionName
import javax.inject.Inject

class ApiHeader @Inject constructor(internal val authApiHeader: AuthApiHeader) {

  data class AuthApiHeader @Inject constructor(
    @VersionName @Expose @SerializedName("Content-Type") val _content_type: String,
    @VersionName @Expose @SerializedName("keyHash") val _key_hash: String
//    @AppKey @Expose @SerializedName("app_key") val _app_key: String,
//    @PackageName @Expose @SerializedName("package") val _package: String,
//    @VersionName @Expose @SerializedName("version") val _version: String,
  )
}