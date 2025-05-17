import android.content.Context
import com.example.fitnesstogether.domain.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://147.45.156.158:5000"

    fun create(context: Context): ApiService {
        // сначала Build настройки Retrofit без Authenticator
        val retrofitBase = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // собственно AuthInterceptor
        val authInterceptor = AuthInterceptor(context)

        // и Authenticator, если нужен автоматический рефреш
        val authenticator = TokenAuthenticator(context, retrofitBase)

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .authenticator(authenticator) // опционально
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}