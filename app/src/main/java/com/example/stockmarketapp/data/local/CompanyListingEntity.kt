package com.example.stockmarketapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity //this will create table named CompanyListingEntity in Room DB
data class CompanyListingEntity(
    @PrimaryKey val id:Int?=null,
    val name: String,
    val symbol: String,
    val exchange: String
)