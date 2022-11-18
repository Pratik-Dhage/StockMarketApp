package com.example.stockmarketapp.data.repository

import android.content.res.Resources
import androidx.compose.ui.semantics.SemanticsProperties.Error
import com.example.stockmarketapp.data.local.StockDatabase
import com.example.stockmarketapp.data.mapper.toCompanyListing
import com.example.stockmarketapp.data.remote.StockApi
import com.example.stockmarketapp.domain.model.CompanyListing
import com.example.stockmarketapp.domain.repository.StockRepository
import com.example.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // only one object throughout the app
class StockRepositoryImpl @Inject constructor(
    val api : StockApi,
    val db : StockDatabase
) : StockRepository{

    private val dao = db.dao

    override suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
       return flow{

           emit(Resource.Loading(true))

           val localListings = dao.searchCompanyListing(query)

           emit(Resource.Success(
               data = localListings.map{ it.toCompanyListing() } ))

           val isDBEmpty = localListings.isEmpty() && query.isBlank() // is Database Empty
           val shouldLoadFromCache = !isDBEmpty && !fetchFromRemote

           if(shouldLoadFromCache){
               emit(Resource.Loading(false))
               return@flow
           }

           val remoteListing = try{

           }
           catch(e: IOException){
             e.printStackTrace()
              emit(Resource.Error("Couldn't load Data"))
           }
           catch(e: HttpException){
               e.printStackTrace()
               emit(Resource.Error("Couldn't load Data"))
           }

       }
    }


}