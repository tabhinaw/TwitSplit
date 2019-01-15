package com.istore.twitsplit.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Twit::class], version = 1)
abstract class TwitRoomDatabase : RoomDatabase() {

    abstract fun twitDao(): TwitDao

    companion object {
        @Volatile
        private var INSTANCE: TwitRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): TwitRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TwitRoomDatabase::class.java,
                    "twit_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    //.addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        /*private class WordDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            *//**
         * Override the onOpen method to populate the database.
         * For this sample, we clear the database every time it is created or opened.
         *//*
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }*/

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        fun populateDatabase(twitDao: TwitDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            twitDao.deleteAll()

            var word = Twit("Hello")
            twitDao.insert(word)
            word = Twit("World!")
            twitDao.insert(word)
        }
    }

}
