package project.lonelywheeler.di.repo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.service.*
import project.lonelywheeler.db.service.api.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        userService: UserService,
        motorVehicleService: MotorVehicleService,
        pedestrianVehicleService: PedestrianVehicleService,
        equipmentService: EquipmentService,
        favoriteOfferService: FavoriteOfferService,
        offerService: OfferService,
    ): Repository {
        return Repository(
            userService,
            motorVehicleService,
            pedestrianVehicleService,
            equipmentService,
            offerService,
            favoriteOfferService
        )
    }

    @Singleton
    @Provides
    fun provideUserService(
        userApi: UserApi,
    ): UserService {
        return UserService(userApi)
    }

    @Singleton
    @Provides
    fun provideUserApi(
        retrofit: Retrofit,
    ): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMotorVehicleService(
        motorVehicleApi: MotorVehicleApi,
    ): MotorVehicleService {
        return MotorVehicleService(motorVehicleApi)
    }

    @Singleton
    @Provides
    fun provideMotorVehicleApi(
        retrofit: Retrofit,
    ): MotorVehicleApi {
        return retrofit.create(MotorVehicleApi::class.java)
    }


    @Singleton
    @Provides
    fun providePedestrianPoweredVehicleService(
        pedestrianVehicleApi: PedestrianVehicleApi,
    ): PedestrianVehicleService {
        return PedestrianVehicleService(pedestrianVehicleApi)
    }

    @Singleton
    @Provides
    fun providePedestrianPoweredVehicleApi(
        retrofit: Retrofit,
    ): PedestrianVehicleApi {
        return retrofit.create(PedestrianVehicleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideEquipmentService(
        equipmentApi: EquipmentApi,
    ): EquipmentService {
        return EquipmentService(equipmentApi)
    }

    @Singleton
    @Provides
    fun provideEquipmentApi(
        retrofit: Retrofit,
    ): EquipmentApi {
        return retrofit.create(EquipmentApi::class.java)
    }


    @Singleton
    @Provides
    fun provideFavoriteOfferService(
        favoriteOfferApi: FavoriteOfferApi,
    ): FavoriteOfferService {
        return FavoriteOfferService(favoriteOfferApi)
    }

    @Singleton
    @Provides
    fun provideFavoriteOfferApi(
        retrofit: Retrofit,
    ): FavoriteOfferApi {
        return retrofit.create(FavoriteOfferApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOfferService(
        offerApi: OfferApi,
    ): OfferService {
        return OfferService(offerApi)
    }


    @Singleton
    @Provides
    fun provideOfferApi(
        retrofit: Retrofit,
    ): OfferApi {
        return retrofit.create(OfferApi::class.java)
    }


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.4:5050/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}