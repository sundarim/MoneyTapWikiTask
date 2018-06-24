package com.stayhappy.moneytap.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import com.stayhappy.moneytap.R
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView


class DetailsActivity : AppCompatActivity() {

    private lateinit var mWebView: WebView
    private lateinit var mTextViewNoData : TextView
    private lateinit var mProgressBarPageLoading : ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mWebView = findViewById(R.id.webview)
        mTextViewNoData = findViewById(R.id.textview_no_data)
        mProgressBarPageLoading = findViewById(R.id.progress_page_loading)

        mWebView.settings?.javaScriptEnabled = true
        mWebView.settings?.domStorageEnabled = true
        val pageTitle = intent.getStringExtra("pageTitle")

        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                mProgressBarPageLoading.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                mProgressBarPageLoading.visibility= View.GONE
            }
        }


        if (pageTitle.isNullOrEmpty()) {
            mWebView.visibility = View.GONE
            mTextViewNoData.visibility = View.VISIBLE
            mTextViewNoData.text= getString(R.string.msg_no_data_found)

        } else{
            mTextViewNoData.visibility = View.GONE
            mWebView.visibility = View.VISIBLE
            mWebView.invalidate()
            mWebView.loadUrl("https://en.wikipedia.org/wiki/$pageTitle")

        }

    }
}