package com.example.stockmarketapp.data.local

import androidx.room.*

@Dao // Data Access Objects
interface StockDao {

    //FOR INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        companyListingEntities : List<CompanyListingEntity>
    )

    //FOR DELETE
    @Query("DElETE FROM companylistingentity ")
    suspend fun clearCompanyListing()

    @Query( //  % means any sequence of character , Eg:  if name is tesla and it matches %tesla%
        """
            SELECT * FROM 
            companylistingentity 
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
            UPPER(:query) == symbol
        """
    )


    //FOR SEARCHING
    suspend fun searchCompanyListing(query : String) : List<CompanyListingEntity>
}