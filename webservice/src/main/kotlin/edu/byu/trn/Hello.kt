package edu.byu.trn

import edu.byu.trn.models.Movie
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.Properties
import org.json.JSONObject

import spark.Spark.*
import java.io.FileInputStream



fun main(args: Array<String>) {
    val apiKey: String = getAPIKey()

    options(
        "/*"
    ) { request, response ->

        val accessControlRequestHeaders = request
            .headers("Access-Control-Request-Headers")
        if (accessControlRequestHeaders != null) {
            response.header(
                "Access-Control-Allow-Headers",
                accessControlRequestHeaders
            )
        }

        val accessControlRequestMethod = request
            .headers("Access-Control-Request-Method")
        if (accessControlRequestMethod != null) {
            response.header(
                "Access-Control-Allow-Methods",
                accessControlRequestMethod
            )
        }

        "OK"
    }
    val square = { number: Int -> number * number }
    before("*") {req, resp -> resp.header("Access-Control-Allow-Origin", "*")}

    get("/hello") { req, res -> "Hello Web Services!" } // we probably don't need this but it's a good sanity check
    get("/movies") { req, res ->

        val search = req.queryParams("search").replace(' ', '+')

        // Example Call - https://api.themoviedb.org/3/search/movie?api_key=2d964b19c1a591cbb10c9ac29815496e&query=Jack+Reacher&include_adult=false&language=en-US&region=US
        val movies =
            fetchUrl(url = "https://api.themoviedb.org/3/search/movie?api_key=$apiKey&query=$search&include_adult=false&language=en-US&region=US")
        val gson = Gson()
        GsonBuilder().setPrettyPrinting().create()

        val jsonStr = gson.toJson(movies)
        return@get jsonStr

    }
}

fun getAPIKey(): String {
    val properties = Properties()
    val propertiesFile = System.getProperty("user.dir") + "\\src\\config.properties"
    println(propertiesFile)
    val inputStream = FileInputStream(propertiesFile)
    properties.load(inputStream)

    return properties["tmdb_api_key"].toString()
}

fun fetchUrl(url: String): MutableList<Movie> {
    val response = khttp.get(url)
    val obj: JSONObject = response.jsonObject

    val movies = obj.optJSONArray("results")
    var movieList: MutableList<Movie> = mutableListOf()
    for (i in 0..(movies.length() - 1)) {
        if (i > 10)
            break

        val movie = movies.getJSONObject(i)
        val id: Int = movie.optInt("id")
        val title: String = movie.optString("title")
        var poster: String = movie.optString("poster_path")
        poster = "https://image.tmdb.org/t/p/w300/$poster"
        val pop: Double = movie.optDouble("popularity")
        val votes: Int = movie.optInt("vote_count")

        val popularity: String = "$pop out of $votes"

        val m: Movie = Movie(id, title, poster, popularity)

        movieList.add(m)
    }

    return movieList
}
