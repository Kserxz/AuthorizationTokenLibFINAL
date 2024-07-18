package com.example.authorization
import java.io.*
import java.net.*
import com.google.gson.Gson

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


// Interaction with POST API
fun postRequest( mURL: URL, reqParam: String, headers: Map<String,String> ) : Array<String> {
    val conn = mURL.openConnection() as HttpURLConnection
    conn.requestMethod = "POST"
    conn.doOutput = true
    if ( headers.isNotEmpty() ) {
        for ( k in headers.keys ) {
            conn.setRequestProperty( k, headers[k] )
        }
    }
    conn.useCaches = false
    var lines: Array<String> = arrayOf()
    DataOutputStream(conn.outputStream).use { it.writeBytes(reqParam) }

    BufferedReader(InputStreamReader(conn.inputStream)).use { br ->
        var line: String?
        while (br.readLine().also { line = it } != null) {
            lines += line.toString()
        }
    }
    return lines
}


// Interaction with GET API
fun getRequest( mURL: URL ) : Array<String>{
    val conn = mURL.openConnection() as HttpURLConnection
    conn.requestMethod = "GET"
    conn.doOutput = true
    conn.useCaches = false
    var lines: Array<String> = arrayOf()

    BufferedReader(InputStreamReader(conn.inputStream)).use { br ->
        var line: String?
        while (br.readLine().also { line = it } != null) {
            lines += line.toString()
        }
    }
    return lines
}


// Main class of the program
object LoginAuth {
    private var site_name = ""

    fun setWebsite(website: String) {
        site_name = website
    }

    fun getWebsite() : String{
        return site_name
    }
    fun getPictograms() : Pictograms? {
        val url = URL( "https://$site_name/api/v1/login/get-pictograms" )
        // get pictogram here
        try {
            val res = getRequest(url).joinToString()
            val pictograms = Gson().fromJson(res, Pictograms::class.java)
            return pictograms
        } catch ( e: Exception ) {
            return null
        }
    }

    fun getToken(username: String, password: String) : Pair<Errors?, String?> {
        val url = URL("https://$site_name/api/v1/login/pid")
        var reqParam = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")
        reqParam += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")
        val headers: Map<String, String> = mapOf()
        try {
            val postResult = postRequest( url, reqParam, headers).joinToString()
            println(postResult)
            if ("{\"errors\":" in postResult) {

                return Pair( Gson().fromJson(postResult, Errors::class.java), null)
            } else {
                val token = Gson().fromJson( postResult, TokenResponse::class.java)
                return Pair(null, token.token)
            }
        } catch (e: Exception) {
            println(e)
            return Pair(null, null)
        }
    }

    fun getUserinfo( token: String ) : Pair<Errors?, UserInfo?> {
        val url = URL( "https://$site_name/api/v1/information/pid" )
        val headers = mapOf("X-Access-Token" to token)
        val postResult = postRequest( url, "", headers ).joinToString()
        if ( "{\"errors\":" in postResult ) {
            return Pair( Gson().fromJson(postResult, Errors::class.java), null )
        } else {
            val userinfo = Gson().fromJson( postResult, UserInfo::class.java )
            return Pair( null, userinfo )
        }
    }
}

// Example of module's usage
/*fun main() {

    LoginAuth.setWebsite("ait2-vladislav001.amvera.io")
    println("Website: ${LoginAuth.getWebsite()}")

    val username = "_3_4_6_7"
    val password = "_1_1_1_1"

    val (errors, token) = LoginAuth.getToken(username, password)
    if (token.isNullOrEmpty()) {
        if ( errors != null ) {
            for ( e in errors.errors ) {
                println("Error ${e.code}: ${e.title} - ${e.description}")
            }
        } else {
            println("Unknown error")
        }
    } else {
        println("Token is $token")
        //token = "a" + token.slice(1 until token.length - 1 )
        val (errs, userinfo) = LoginAuth.getUserinfo(token)
        if ( userinfo != null ) {
            println("Username: ${userinfo.name}")
            println("Age: ${userinfo.age}")
            println("Gender: ${userinfo.gender}")
        } else {
            if ( errs != null ) {
                for (e in errs.errors) {
                    println("Error ${e.id} (${e.code}): ${e.title} - ${e.description}")
                }
            }
        }
    }

    val pictograms = LoginAuth.getPictograms()?.pictograms
    if (pictograms.isNullOrEmpty()) {
        println("Failed to get pictograms from server")
    } else {
        for ( p in pictograms ) {
            println("Pictogram: ${p.value} - ${p.image}")
        }
    }
}*/