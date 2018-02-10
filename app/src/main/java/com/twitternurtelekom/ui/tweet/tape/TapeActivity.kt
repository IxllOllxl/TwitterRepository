package com.twitternurtelekom.ui.tweet.tape

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.twitternurtelekom.R
import com.twitternurtelekom.service.TapeService
import com.twitternurtelekom.ui.tweet.create.CreateTweetActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_tape.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class TapeActivity : AppCompatActivity(), TapeListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: TapeViewModel
    private var adapter = TapeAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tape)
        init()
    }

    override fun onResume() {
        super.onResume()
        startService(Intent(this, TapeService::class.java))
    }

    override fun onPause() {
        super.onPause()
        stopService(Intent(this, TapeService::class.java))
    }

    private fun init() {
        initToolbar()
        initAdapter()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TapeViewModel::class.java)

        viewModel.fetchTweets().observe(this, Observer {
            adapter.setList(it)
            swipe_refresh.isRefreshing = false
        })

        swipe_refresh.setOnRefreshListener {
            viewModel.updateTweets()
        }
        create_tweet.setOnClickListener {
            CreateTweetActivity.start(this)
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun initAdapter() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.itemAnimator = null
        recycler_view.adapter = adapter
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, TapeActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
    }
}