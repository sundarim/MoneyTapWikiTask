package com.stayhappy.moneytap.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Page{
    var pageid: Int? = null
    var ns: Int? = null
    var title: String? = null
    var index: Int? = null
    var thumbnail: Thumbnail? = null
    var terms: Terms? = null
}