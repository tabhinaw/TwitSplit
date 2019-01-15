package com.istore.twitsplit.addtwit

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.istore.twitsplit.R

class AddTwitActivity : AppCompatActivity() {

    private lateinit var etAddTwitView: EditText

    private lateinit var addTwitViewModel: AddTwitViewModel

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_twit)
        etAddTwitView = findViewById(R.id.et_add_twit)

        addTwitViewModel = ViewModelProviders.of(this).get(AddTwitViewModel::class.java)

        addTwitViewModel.observeResult().observe(this, Observer { result -> onResultChanged(result) })

        val button = findViewById<Button>(R.id.button_post)
        button.setOnClickListener {
            val twit = etAddTwitView.text.toString()
            val twitList = addTwitViewModel.splitMessage(twit)
            println(twitList)
            addTwitViewModel.insertTwit(twitList)
            /*replyIntent.putExtra(EXTRA_REPLY, twit)
            setResult(Activity.RESULT_OK, replyIntent)*/
            /*if (TextUtils.isEmpty(etAddTwitView.text)) {
                //setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {

            }*/
        }
    }

    private fun onResultChanged(result: AddTwitResult?) {

        when (result) {
            AddTwitResult.VERIFY_TWIT_PATTERN_FAILED -> {
                Toast.makeText(
                    applicationContext,
                    R.string.pattern_not_matched,
                    Toast.LENGTH_LONG
                ).show()
            }

            AddTwitResult.EMPTY_TWIT -> {
                Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
            }

            AddTwitResult.TWIT_ADDED -> {
                finish()
            }
        }
    }

    private fun insertTwitInDB() {

    }

    companion object {
        const val EXTRA_REPLY = "com.istore.twitsplit.REPLY"
    }
}
