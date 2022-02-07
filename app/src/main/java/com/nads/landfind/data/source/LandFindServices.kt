package com.nads.landfind.data.source

import com.nads.landfind.data.DataValues
import okhttp3.MultipartBody
import retrofit2.http.*





interface LandFindServices {
    //Gets the login done
    @FormUrlEncoded
    @POST("login")
    suspend fun login(@Field("username")username:String,
                      @Field("password")password:String): DataValues

    @FormUrlEncoded
    @POST("signup")
    suspend fun signup(@Field("username")username:String,
                      @Field("password")password:String):DataValues

    @FormUrlEncoded
    @POST("getlandlists")
    suspend fun getlandlists(@Field("id")Id:String?,
                            @Field("placename")placename:String?,
                            @Field("village")village:String?,
                            @Field("hbtaluk")hbtaluk:String?,
                            @Field("propertiesland")propertiesland:String?,
                            @Field("otherspec")otherspec:String?,
                            @Field("landtype")landtype:String?,
                            @Field("price")price:String?,
                            @Field("pagenumber")pagenumber:Int): DataValues
    @FormUrlEncoded
    @POST("addlandlist")
    suspend fun addlandlist(@Field("username") username:String?,
                            @Field ("img1")img1: String?,
                            @Field ("img2")img2: String?,
                            @Field ("img3")img3: String?,
                            @Field("google_map") google_map:String?,
                            @Field("place_name") place_name:String?,
                            @Field("village") village:String?,
                            @Field("hb_taluk") hb_taluk:String?,
                            @Field("properties_land") properties_land:String?,
                            @Field("other_spec") other_spec:String?,
                            @Field("land_type") land_type:String?,
                            @Field("leagal_issues") leagal_issues:String?,
                            @Field("contact_num") contact_num:String?,
                            @Field("address") address:String?,
                            @Field("price") price:String?): DataValues
    @FormUrlEncoded
    @POST("getland")
    suspend fun getLand(@Field("id")id:String?):DataValues

    @FormUrlEncoded
    @POST("otp")
    suspend fun getOtp(@Field("otp")otp:String?):DataValues

}