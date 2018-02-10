package com.twitternurtelekom.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.parceler.Parcel

@Entity(tableName = "tweets")
@Parcel
data class Tweet(@PrimaryKey
                 var id: String = "",
                 var text: String = "",
                 @SerializedName("created_at")
                 var createdAt: String = "",
                 @Embedded var user: User? = null)