package com.stayhappy.moneytap.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose



class Wikipedia{

    var batchcomplete: Boolean? = null
    @SerializedName("continue")
    var _continue: Continue? = null
    var query: Query? = null


}