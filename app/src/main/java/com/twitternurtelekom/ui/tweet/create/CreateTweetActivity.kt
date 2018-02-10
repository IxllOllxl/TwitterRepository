package com.twitternurtelekom.ui.tweet.create

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.twitternurtelekom.R
import com.twitternurtelekom.utils.subscribe
import com.twitternurtelekom.utils.text
import com.twitternurtelekom.utils.toast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_create_tweet.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class CreateTweetActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: CreateTweetViewModel
    private lateinit var saveItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_tweet)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_done, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        saveItem = menu.findItem(R.id.menu_done)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_done -> {
                viewModel.saveTweet(status.text())
                showProgress(true)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        initToolbar()
        initControls()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CreateTweetViewModel::class.java)

        viewModel.eventUtil.observe(this, Observer {
            when (it?.first) {
                CreateTweetViewModel.tweet_created -> {
                    showProgress(false)
                    toast(it.second as String)
                    finish()
                }
                CreateTweetViewModel.tweet_failed -> {
                    showProgress(false)
                    toast(it.second as String)
                }
            }
        })
    }

    private fun initControls() {
        status.subscribe(onTextChanged = { _, _, _, _ ->
            saveItem.isEnabled = status.length() > 5
        })
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.create_tweet)
        toolbar.setNavigationOnClickListener { finish() }
    }

    private fun showProgress(show: Boolean) {
        login_form.visibility = if (show) View.GONE else View.VISIBLE
        login_form.animate()
                .setDuration(500)
                .alpha((if (show) 0 else 1).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        login_form.visibility = if (show) View.GONE else View.VISIBLE
                    }
                })

        login_progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, CreateTweetActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
    }
}