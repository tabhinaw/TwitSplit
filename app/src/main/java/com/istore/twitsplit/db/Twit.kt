package com.istore.twitsplit.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "twit_table")
data class Twit(@PrimaryKey @ColumnInfo(name = "twit") val twit: String)