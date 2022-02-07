package com.nads.landfind.domain

import com.nads.landfind.data.DataValues
import com.nads.landfind.data.Result
import com.nads.landfind.data.source.LandFindDefaultRepository
import com.nads.landfind.data.source.LandFindRepository
import com.nads.landfind.util.wrapEspressoIdlingResource
import javax.inject.Inject

class GetLoginUseCases @Inject constructor(private val  landFindDefaultRepository: LandFindRepository) {

    suspend operator fun invoke(username:String,password:String): Result<DataValues> {

        wrapEspressoIdlingResource {
            return landFindDefaultRepository.login(username,password)
        }
    }



}