import android.content.Context

object TokenStorage {
    private const val PREFS_NAME = "auth_prefs"
    private const val KEY_ACCESS  = "ACCESS_TOKEN"
    private const val KEY_REFRESH = "REFRESH_TOKEN"

    private fun prefs(context: Context) =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveTokens(context: Context, accessToken: String, refreshToken: String) {
        prefs(context).edit()
            .putString(KEY_ACCESS, accessToken)
            .putString(KEY_REFRESH, refreshToken)
            .apply()
    }

    fun getAccessToken(context: Context): String? =
        prefs(context).getString(KEY_ACCESS, null)

    fun getRefreshToken(context: Context): String? =
        prefs(context).getString(KEY_REFRESH, null)
}