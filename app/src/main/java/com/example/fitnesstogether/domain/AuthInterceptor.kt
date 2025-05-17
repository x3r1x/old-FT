import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val token = TokenStorage.getAccessToken(context)
        return if (token.isNullOrEmpty()) {
            chain.proceed(original)
        } else {
            val authorized = original.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(authorized)
        }
    }
}