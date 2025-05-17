import android.content.Context
import com.example.fitnesstogether.domain.ApiService
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response        // это okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit

class TokenAuthenticator(
    private val context: Context,
    private val retrofit: Retrofit
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // во избежание петель
        if (responseCount(response) > 1) return null

        val refreshToken = TokenStorage.getRefreshToken(context) ?: return null

        // синхронный вызов на /User/Refresh
        val service = retrofit.create(ApiService::class.java)
        val refreshCall = service.refreshToken(RefreshRequest(refreshToken)).execute()

        return if (refreshCall.isSuccessful) {
            val newTokens = refreshCall.body()!!
            TokenStorage.saveTokens(context, newTokens.accessToken, newTokens.refreshToken)

            // повторяем оригинальный запрос: используем метод request()
            response.request()
                .newBuilder()
                .header("Authorization", "Bearer ${newTokens.accessToken}")
                .build()
        } else {
            null
        }
    }

    private fun responseCount(response: Response): Int {
        var res: Response? = response
        var count = 0
        while (res != null) {
            count++
            // и тут – метод priorResponse()
            res = res.priorResponse()
        }
        return count
    }
}