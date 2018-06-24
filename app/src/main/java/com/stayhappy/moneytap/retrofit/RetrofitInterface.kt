package com.stayhappy.moneytap.retrofit

import com.stayhappy.moneytap.model.Wikipedia
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitInterface{
    @GET("api.php")
    fun getSearchResults(@Query("action") action: String,
                         @Query("format") format: String,
                         @Query("formatversion") formatVersion: String,
                         @Query("prop") prop: String,
                         @Query("generator") generator: String,
                         @Query("redirects") redirect: String,
                         @Query("piprop") piprop: String,
                         @Query("pithumbsize") piThumbSize: String,
                         @Query("pilimit") piLimit: String,
                         @Query("wbptterms") description: String,
                         @Query("gpssearch") gpsSearch: String,
                         @Query("gpslimit") limit: String,
                         @Query("inprop") inProp:String): Call<Wikipedia>

}