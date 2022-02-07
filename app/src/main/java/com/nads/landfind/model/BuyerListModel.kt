package com.nads.landfind.model

import android.view.View
import androidx.annotation.DrawableRes
import androidx.databinding.BaseObservable
import com.nads.landfind.R

class BuyerListModel( @DrawableRes val img:Int,val onClick:()->Unit,
                      val id:Int?=null,
                      val place_name:String?=null,
                      val village:String?=null,
                      val hb_taluk:String?=null,
                      val properties_land:String?=null,
                      val land_type:String?=null,
                      val price:String?=null):ItemViewModel, BaseObservable() {
    override val layoutId: Int = R.layout.land_list_cards
    fun onClick(view: View?) {
        onClick()
    }
}