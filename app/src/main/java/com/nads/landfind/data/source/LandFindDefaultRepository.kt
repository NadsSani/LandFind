package com.nads.landfind.data.source

import android.util.Log
import com.nads.landfind.data.DataValues
import com.nads.landfind.data.Result
import javax.inject.Inject
import com.nads.landfind.data.Result.Success
import com.nads.landfind.data.Result.Error
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import javax.inject.Singleton
import kotlin.Exception

@Singleton
class LandFindDefaultRepository @Inject constructor(
    private val landFindServices: LandFindServices,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO):
    LandFindRepository {

    override suspend fun getLandList(id:String?,
                                     place_name:String?,
                                     village:String?,
                                     hb_taluk:String?,
                                     properties_land:String?,
                                     other_spec:String?,
                                     land_type:String?,
                                     price:String?,pagenumber:Int): Result<DataValues>
    = withContext(ioDispatcher) {

     val landlist = landFindServices.getlandlists(
                id,
                place_name,
                village,
                hb_taluk,
                properties_land,
                other_spec,
                land_type,
                price,pagenumber)
     if (landlist.status){
         return@withContext Success(landlist)
     }else{
         return@withContext Error(Exception("Failed To Load Land List"))
     }

        }


    override suspend fun login(username:String,password:String):Result<DataValues> = withContext(ioDispatcher) {
       val login =  landFindServices.login(username,password)
        if (login.status){
            return@withContext Success(login)
        }
        else
        {
            return@withContext Error(Exception("Login Error"))
        }
    }

    override suspend fun signup(username:String,password:String):Result<DataValues> = withContext(ioDispatcher){
      val signup = landFindServices.signup(username,password)
        if (signup.status == true){
            return@withContext Success(signup)
        }
        else{
            return@withContext Error(Exception("Signup failed"))
        }


    }


    override suspend fun getLand(id:String?): Result<DataValues> = withContext(ioDispatcher){
        val getlandresult = landFindServices.getLand(id)
        if (getlandresult.status == true) {
            return@withContext Success(getlandresult)
        } else {
            return@withContext Error(Exception("Resources Not Available"))
        }
    }

    override suspend fun getOtp(otp:String?): Result<DataValues> = withContext(ioDispatcher){
        val getotpresult = landFindServices.getOtp(otp)
        if (getotpresult.status == true) {
            Log.e("WON","WON")
            return@withContext Success(getotpresult)
        } else {
            return@withContext Error(Exception("Resources Not Available"))
        }
    }


    override suspend fun submitLand(username: String?,
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
                                    price:String?):Result<DataValues>? = withContext(ioDispatcher) {


        val data = landFindServices.addlandlist(username,
            img1,
            img2,
            img3,
            google_map,
            place_name,
            village,
            hb_taluk,
            properties_land,
            other_spec,
            land_type,
            leagal_issues,
            contact_num,
            address,
            price)
        if (data.status == true){
            return@withContext Success(data)

        }
        else{
           return@withContext Error(Exception("Failed to upload data"))

        }



    }



    }