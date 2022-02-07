package com.nads.landfind.domain

import android.util.Log
import com.nads.landfind.data.DataValues
import com.nads.landfind.data.Result
import com.nads.landfind.data.source.LandFindDefaultRepository
import com.nads.landfind.util.wrapEspressoIdlingResource
import okhttp3.MultipartBody
import javax.inject.Inject

class GetSubmitUseCases @Inject constructor(private val landFindDefaultRepository: LandFindDefaultRepository) {
    suspend operator fun invoke(username:String?,
                                img1:String?,
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
                                price:String?): Result<DataValues>? {
        wrapEspressoIdlingResource {



        return landFindDefaultRepository.submitLand(username,
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

    }
    }

}