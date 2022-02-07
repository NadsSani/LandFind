package com.nads.landfind.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "landtable")
data class Land (
    @PrimaryKey  @ColumnInfo(name = "id")val id:Int?,
    @ColumnInfo(name = "placename")val placename:String?,
    @ColumnInfo(name = "img1")val img1:String?,
    @ColumnInfo(name = "img2")val img2:String?,
    @ColumnInfo(name = "img3")val img3:String?,
    @ColumnInfo(name = "village")val village:String?,
    @ColumnInfo(name = "hbtaluk")val hbtaluk:String?,
    @ColumnInfo(name = "googlemap")val googlemap:String?,
    @ColumnInfo(name = "propertiesland")val propertiesland:String?,
    @ColumnInfo(name = "otherspec")val otherspec:String?,
    @ColumnInfo(name = "legalissues")val legalissues:String?,
    @ColumnInfo(name = "landtype")val landtype:String?,
    @ColumnInfo(name = "contactnum")val contactnum:String?,
    @ColumnInfo(name = "address")val address:String?,
    @ColumnInfo(name = "price")val price:String?
        )