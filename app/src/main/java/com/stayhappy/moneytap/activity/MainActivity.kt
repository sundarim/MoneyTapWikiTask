package com.stayhappy.moneytap.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.stayhappy.moneytap.R
import com.stayhappy.moneytap.adapter.SearchListRecyclerAdapter
import com.stayhappy.moneytap.model.Page
import com.stayhappy.moneytap.model.Wikipedia
import com.stayhappy.moneytap.retrofit.RetrofitClient
import com.stayhappy.moneytap.retrofit.RetrofitInterface
import com.stayhappy.moneytap.utils.closeKeyBoard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {


    private lateinit var mEditText: EditText
    private lateinit var mTextViewSearchText: TextView
    private lateinit var mProgressBarSearchLoading : ProgressBar

    private lateinit var mSearchListRecyclerAdapter: SearchListRecyclerAdapter
    private lateinit var mRecyclerViewSearchList : RecyclerView

    var gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mEditText = findViewById(R.id.edit_search)
        mProgressBarSearchLoading = findViewById(R.id.progressbar_search)
        mTextViewSearchText = findViewById(R.id.textview_search_data)

        mRecyclerViewSearchList = findViewById(R.id.recyclerView)
        mRecyclerViewSearchList.layoutManager = LinearLayoutManager(applicationContext)
        mSearchListRecyclerAdapter = SearchListRecyclerAdapter()
        mRecyclerViewSearchList.adapter = mSearchListRecyclerAdapter
        mSearchListRecyclerAdapter.setOnSearchItemClickListener(object : SearchListRecyclerAdapter.onSearchItemClick{
            override fun onSearchItemClicked(title: String) {
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                intent.putExtra("pageTitle", title)
                startActivity(intent)
            }
        })

        mEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(text: Editable?) {
               doSearch(text.toString())
            }

            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                doSearch(text.toString())
            }

        })


    }

    override fun onPause() {
        closeKeyBoard(applicationContext,mEditText)
        super.onPause()
    }

    fun doSearch(searchString: String) {
        mProgressBarSearchLoading.visibility = View.VISIBLE

        val apiService = RetrofitClient.getClient(applicationContext)?.create(RetrofitInterface::class.java)
        val call = apiService?.getSearchResults("query","json","2","pageimages|pageterms",
                "prefixsearch", "1","thumbnail","50","10","description",
                searchString, "20","url")
        call?.enqueue(object : Callback<Wikipedia> {
            override fun onResponse(call: Call<Wikipedia>?, response: Response<Wikipedia>?) {
                mProgressBarSearchLoading.visibility = View.GONE
                mRecyclerViewSearchList.visibility=View.VISIBLE
                mTextViewSearchText.visibility = View.GONE
                closeKeyBoard(applicationContext,mEditText)
                mEditText.clearFocus()
                if(response?.body()?.query?.pages != null) {
                    mSearchListRecyclerAdapter.setData(response.body()?.query?.pages as MutableList<Page>)
                }
            }

            override fun onFailure(call: Call<Wikipedia>?, t: Throwable?) {
                mProgressBarSearchLoading.visibility = View.GONE
                mRecyclerViewSearchList.visibility=View.GONE
                mTextViewSearchText.visibility = View.VISIBLE
            }

        })

    }


}
