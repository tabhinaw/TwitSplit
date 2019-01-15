package com.istore.twitsplit.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface TwitDao {

    @Query("SELECT * from twit_table")
    fun getTwits(): LiveData<List<Twit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(twit: Twit)

    @Query("DELETE FROM twit_table")
    fun deleteAll()
}