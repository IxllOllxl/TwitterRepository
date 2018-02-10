package com.twitternurtelekom.model

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

@Parcel
class User(var name: String = "",
           @SerializedName("profile_image_url")
           var avatar: String = "")