package com.stayhappy.moneytap.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Continue{
    var gpsoffset: Int? = null
    @SerializedName("continue")
    @Expose
    var _continue: String? = null
}