package com.istore.twitsplit.addtwit

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import com.istore.twitsplit.db.Twit
import com.istore.twitsplit.db.TwitRoomDatabase
import kotlin.math.roundToInt

class AddTwitViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AddTwitRepository
    private val resultMutableLiveData = MutableLiveData<AddTwitResult>()

    init {
        val twitDao = TwitRoomDatabase.getDatabase(application).twitDao()
        repository = AddTwitRepository(twitDao)
    }

    fun observeResult() : MutableLiveData<AddTwitResult> {
        return resultMutableLiveData
    }

    fun insert(twit: Twit) {
        repository.insert(twit)
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun splitMessage(message:String) : ArrayList<String> {
        val messageList = ArrayList<String>()
        if (!TextUtils.isEmpty(message)) {
            val splitLength = Math.ceil((message.length / 50.0f).toDouble())
            if (splitLength > 1) {
                var i = 0
                while (i < splitLength) {
                    if (i.toDouble() != splitLength - 1) {
                        var splitMessage = message.substring((i * 49), ((i + 1) * 49))

                        var charStr = message[(((i+1) * 49))]
                        if (!charStr.toString().equals(" ")) {
                            messageList.clear()
                            resultMutableLiveData.value = AddTwitResult.VERIFY_TWIT_PATTERN_FAILED
                            break;
                        }
                        val finalMessage = "${i + 1} / ${splitLength.roundToInt()} $splitMessage"
                        messageList.add(finalMessage)
                    }
                    else {
                        val splitMessage = message.substring((i * 4), message.length)
                        val finalMessage = "${i + 1} / ${splitLength.roundToInt()} $splitMessage"
                        messageList.add(finalMessage)
                    }
                    i++
                }
            }
            else {
                val splitMessage = message.substring(0, message.length)
                messageList.add(splitMessage)
            }
        } else {
            resultMutableLiveData.value = AddTwitResult.EMPTY_TWIT
        }
        return messageList
    }

    fun insertTwit(twitList: ArrayList<String>) {
        var i = 0
        while (i < twitList.size) {
            val twit = Twit(twitList[i])
            insert(twit)
            i++
        }
        resultMutableLiveData.value = AddTwitResult.TWIT_ADDED

    }
}
