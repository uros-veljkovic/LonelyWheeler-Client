package project.lonelywheeler.db.response

import com.google.gson.annotations.SerializedName

class MyResponse<T>
constructor(
    @SerializedName("message")
    val message: String,
    @SerializedName("entity")
    val entity: T?
)