package com.example.myapplication3.Interface

import com.example.myapplication3.Model.LoginModel
import com.example.myapplication3.Model.dataModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Apiinterface
{
    @FormUrlEncoded
    @POST("Login.php")
    fun logindata (
        @Field("enrollment") enrollment: String?,
        @Field("password") password : String?,
    ): Call<List<LoginModel>>

    @GET("paper.php")
    fun paperView(): Call<List<dataModel>>

    @GET("notes.php")
    fun notesView(): Call<List<dataModel>>

    @GET("books.php")
    fun booksView(): Call<List<dataModel>>

    @GET("assignment.php")
    fun assignmnetView(): Call<List<dataModel>>
}