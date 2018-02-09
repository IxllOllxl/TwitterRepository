package com.twitternurtelekom.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.parceler.Parcel

@Entity(tableName = "twits")
@Parcel
class Twit(@PrimaryKey
           var id: String = "",
           var title: String = "",
           var description: String = "",
           var avatar: String = "")