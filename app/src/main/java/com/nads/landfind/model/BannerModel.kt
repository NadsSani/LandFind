package com.nads.landfind.model

import androidx.annotation.DrawableRes
import androidx.databinding.BaseObservable
import com.nads.landfind.R

class BannerModel( val img:String?):ItemViewModel,BaseObservable() {
    override val layoutId: Int = R.layout.banner_list
}