package com.example.authorization

import android.os.AsyncTask

import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

import java.io.*
import java.net.*
import java.util.concurrent.*
import com.google.gson.Gson
import kotlinx.coroutines.*


// Handy classes for deserialization
class UserInfo {
    val name: String = ""
    val age: Int = 0
    val gender: String = ""
}
class Pictogram {
    val value: String = ""
    val image: String = ""
}
class Pictograms {
    val pictograms: Array<Pictogram> = arrayOf()
}
class Error {
    val id: Int = 0
    val code: Int = 0
    val title: String = ""
    val description: String = ""
}
class Errors {
    val errors: Array<Error> = arrayOf()
}
class TokenResponse {
    val token: String = ""
}


// Callback functions
fun unwrapPictograms( response: String ) : Array<Pictogram>? {
    if ("{\"errors\":" !in response) {
        val pictograms = Gson().fromJson(response, Pictograms::class.java)
        // Download pictures from Internet and show them on the screen
        if ( pictograms != null ) {
            var pictos = pictograms?.pictograms
            return pictos
            /*if(pictos != null) {
            	for (p in pictos) {
                	println("${p.value} - https://${p.image}")
            	}
            }*/
        }
    }
    return null
}

fun unwrapToken( response: String ) : String? {
    if ("{\"errors\":" !in response) {
        val token  = Gson().fromJson(response, TokenResponse::class.java)
        // Store token somewhere...
        if ( token != null ) {
            var tkn = token?.token
            println("Token: ${tkn}")
            return tkn
        }
    }
    return null
}

fun unwrapUserInfo( response: String ) : UserInfo? {
    if ("{\"errors\":" !in response) {
        val userinfo = Gson().fromJson(response, UserInfo::class.java)
        // Show information using GUI here...
        if ( userinfo != null ) {
            println("UserInfo: ${userinfo?.name}, ${userinfo?.age}, ${userinfo?.gender}")
            return userinfo
        }
    }
    return null
}

// The core of backend
class Authorization(website: String) {
    private var site_name = website

    fun getPictograms( callback: (String) -> Unit ) {
        val request: Request = Request.Builder().url("https://$site_name/api/v1/login/get-pictograms").build()
        AsyncHttpRequest( callback ).execute( request )
    }

    fun getToken( login: String, password: String, callback: (String) -> Unit ) {
        var formBody: RequestBody = FormBody.Builder().add("login", login)
            .add("password", password)
            .build()
        var request: Request = Request.Builder().url("https://$site_name/api/v1/login/pid").post(formBody).build()
        AsyncHttpRequest( callback ).execute(request)
    }

    fun getUserInfo( token: String, callback: (String) -> Unit ) {
        val client = OkHttpClient.Builder().addNetworkInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .header("X-Access-Token", token).build()
            )
        }.build()

        val request = Request.Builder().url("https://$site_name/api/v1/information/pid").build()
        try {
            val response = client.newCall( request ).execute()
            if (!response.isSuccessful) {
                return;
            }
            val result = response.body!!.string()
            callback( result )

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    public class AsyncHttpRequest( callback_: (String) -> Unit ) : AsyncTask<Request, Void, Response>() {

        val callback = callback_

        override protected fun doInBackground(vararg requests: Request) : Response? {
            var response: Response? = null
            try {
                var client: OkHttpClient = OkHttpClient()
                response = client.newCall( requests[0] ).execute()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return response
        }

        override protected fun onPostExecute( response: Response ) {
            super.onPostExecute( response )
            try {
                var resStr: String = response.body!!.string().toString()
                callback( resStr )
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }
}



