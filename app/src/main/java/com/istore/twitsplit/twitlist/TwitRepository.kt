package com.istore.twitsplit.twitlist

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.istore.twitsplit.db.DbWorkerThread
import com.istore.twitsplit.db.Twit
import com.istore.twitsplit.db.TwitDao

class TwitRepository(private val twitDao: TwitDao) {
    private var mDbWorkerThread: DbWorkerThread

    init {
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allTwits: LiveData<List<Twit>> = twitDao.getTwits()

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun insert(twit: Twit) {
        val task = Runnable { twitDao.insert(twit) }
        mDbWorkerThread.postTask(task)

    }
}