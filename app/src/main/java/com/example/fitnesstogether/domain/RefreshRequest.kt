import com.google.gson.annotations.SerializedName

data class RefreshRequest(@SerializedName("refreshToken") val refreshToken: String)