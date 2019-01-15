package com.istore.twitsplit.twitlist

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.istore.twitsplit.db.Twit
import com.istore.twitsplit.db.TwitRoomDatabase

class TwitViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TwitRepository
    val allTwits: LiveData<List<Twit>>

    init {
        val twitDao = TwitRoomDatabase.getDatabase(application).twitDao()
        repository = TwitRepository(twitDao)
        allTwits = repository.allTwits
    }

    override fun onCleared() {
        super.onCleared()
    }
}
