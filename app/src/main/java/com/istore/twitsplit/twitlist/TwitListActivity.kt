package com.istore.twitsplit.twitlist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.istore.twitsplit.R
import com.istore.twitsplit.addtwit.AddTwitActivity

class TwitListActivity : AppCompatActivity() {
    private lateinit var twitViewModel: TwitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twit_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TwitListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        twitViewModel = ViewModelProviders.of(this).get(TwitViewModel::class.java)

        twitViewModel.allTwits.observe(this, Observer { twits ->
            twits?.let { adapter.setTwits(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@TwitListActivity, AddTwitActivity::class.java)
            startActivity/*ForResult*/(intent/*, TWIT_REQUEST_CODE*/)
        }
    }
}

