package com.nads.landfind.data

data class DataValues (
    val datavalues: List<Land>,
    val nextpage: Int?,
    val perpage: Int,
    val previouspage:Int?,
    val status: Boolean,
    val total: Int,
    val totalpages: Int
    )
