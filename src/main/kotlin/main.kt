import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.github.cdimascio.dotenv.dotenv

suspend fun main() {

    val dotenv = dotenv()
    val token = dotenv["BEARER_TOKEN"]

    val client = HttpClient()
    val response: HttpResponse = client.get("https://api.twitter.com/2/tweets/search/recent?query=\"ghana technology\"&tweet.fields=author_id,created_at,entities,geo,in_reply_to_user_id,lang,possibly_sensitive,referenced_tweets,source&max_results=10") {
        headers {
            append(HttpHeaders.Accept, "application/json")
            append(HttpHeaders.Authorization, "Bearer $token")
            append(HttpHeaders.UserAgent, "ktor client")
        }
    }
    val stringBody: String = response.receive()
    println(response.status)
    println(stringBody)
    client.close()
}