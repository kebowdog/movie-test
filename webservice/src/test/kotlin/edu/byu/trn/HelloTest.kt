package edu.byu.trn

import org.junit.Test
import kotlin.test.assertEquals

class HelloTest {
    @Test
    fun `Jack Reacher has two results`() {
        val search = "Jack+Reacher"
        val apiKey = getAPIKey()
        val movies =
            fetchUrl(url = "https://api.themoviedb.org/3/search/movie?api_key=$apiKey&query=$search&include_adult=false&language=en-US&region=US")
        assertEquals(movies.count(), 2)
    }
}
