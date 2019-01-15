package com.istore.twitsplit.addtwit

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import com.istore.twitsplit.db.DbWorkerThread
import com.istore.twitsplit.db.Twit
import com.istore.twitsplit.db.TwitDao

class AddTwitRepository(private val twitDao: TwitDao) {
    private var mDbWorkerThread: DbWorkerThread = DbWorkerThread("dbWorkerThread")

    init {
        mDbWorkerThread.start()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun insert(twit: Twit) {
        val task = Runnable { twitDao.insert(twit) }
        mDbWorkerThread.postTask(task)

    }
}