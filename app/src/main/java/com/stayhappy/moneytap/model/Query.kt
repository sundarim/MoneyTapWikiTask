package com.stayhappy.moneytap.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Query{
    var redirects: List<Redirect>? = null
    var pages: List<Page>? = null
}