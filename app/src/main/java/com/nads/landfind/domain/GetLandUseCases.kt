package com.nads.landfind.domain

import com.nads.landfind.data.DataValues
import com.nads.landfind.data.Land
import com.nads.landfind.data.Result
import com.nads.landfind.data.source.LandFindDefaultRepository
import com.nads.landfind.data.source.LandFindRepository
import com.nads.landfind.util.wrapEspressoIdlingResource
import javax.inject.Inject

class GetLandUseCases @Inject constructor(private val landFindDefaultRepository: LandFindRepository) {

    suspend operator fun invoke(id:String?): Result<DataValues> {
        wrapEspressoIdlingResource {
            return landFindDefaultRepository.getLand(id)
        }

    }

}