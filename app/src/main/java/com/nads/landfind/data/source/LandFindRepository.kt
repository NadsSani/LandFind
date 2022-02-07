package com.nads.landfind.data.source

import com.nads.landfind.data.DataValues
import com.nads.landfind.data.Land
import com.nads.landfind.data.Result
import okhttp3.MultipartBody
import retrofit2.http.Field

interface LandFindRepository {
    suspend fun getLandList(Id:String?,
                          place_name:String?,
                            village:String?,
                            hb_taluk:String?,
                           properties_land:String?,
                           other_spec:String?,
                            land_type:String?,
                         price:String?,pagenumber:Int): Result<DataValues>

    suspend fun login(username:String,password:String): Result<DataValues>

    suspend fun signup(username:String,password:String):Result<DataValues>

    suspend fun getLand(Id:String?):Result<DataValues>
    suspend fun getOtp(otp:String?):Result<DataValues>
    suspend fun submitLand(username: String?,
                           img1: String?,
                           img2:String?,
                           img3:String?,
                           google_map:String?,
                           place_name:String?,
                           village:String?,
                           hb_taluk:String?,
                           properties_land:String?,
                           other_spec:String?,
                           land_type:String?,
                           leagal_issues:String?,
                           contact_num:String?,
                           address:String?,
                           price:String?): Result<DataValues>?
}