package project.lonelywheeler.model.domain.product.vehicle.motor

import com.google.gson.annotations.SerializedName

enum class CarBodyType {

    @SerializedName("coupe")
    Coupe,

    @SerializedName("convertible")
    Convertible,

    @SerializedName("hatchback")
    Hatchback,

    @SerializedName("minivan")
    Minivan,

    @SerializedName("truck")
    Truck,

    @SerializedName("pick up")
    PickUp,

    @SerializedName("sedan")
    Sedan,

    @SerializedName("station wagon")
    StationWagon,

    @SerializedName("sports car")
    SportsCar,

    @SerializedName("suv")
    SUV,

    @SerializedName("van")
    Van
}