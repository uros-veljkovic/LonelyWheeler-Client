package project.lonelywheeler.di.repo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import project.lonelywheeler.db.repo.Repository
import project.lonelywheeler.db.service.EquipmentService
import project.lonelywheeler.db.service.PedestrianVehicleService
import project.lonelywheeler.db.service.MotorVehicleService
import project.lonelywheeler.db.service.UserService
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
        equipmentService: EquipmentService
    ): Repository {
        return Repository(
            userService,
            motorVehicleService,
            pedestrianVehicleService,
            equipmentService
        )
    }

    @Singleton
    @Provides
    fun provideUserService(
        userApi: UserApi
    ): UserService {
        return UserService(userApi)
    }

    @Singleton
    @Provides
    fun provideUserApi(
        retrofit: Retrofit
    ): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMotorVehicleService(
        motorVehicleApi: MotorVehicleApi
    ): MotorVehicleService {
        return MotorVehicleService(motorVehicleApi)
    }

    @Singleton
    @Provides
    fun provideMotorVehicleApi(
        retrofit: Retrofit
    ): MotorVehicleApi {
        return retrofit.create(MotorVehicleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHumanPoweredVehicleService(
        pedestrianVehicleApi: PedestrianVehicleApi
    ): PedestrianVehicleService {
        return PedestrianVehicleService(pedestrianVehicleApi)
    }

    @Singleton
    @Provides
    fun provideHumanPoweredVehicleApi(
        retrofit: Retrofit
    ): PedestrianVehicleApi {
        return retrofit.create(PedestrianVehicleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideEquipmentService(
        equipmentApi: EquipmentApi
    ): EquipmentService {
        return EquipmentService(equipmentApi)
    }

    @Singleton
    @Provides
    fun provideEquipmentApi(
        retrofit: Retrofit
    ): EquipmentApi {
        return retrofit.create(EquipmentApi::class.java)
    }

    //TODO: umesto baseUrl uneti moju IP adresu (valjda)
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.4:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//   "mongodb+srv://urkeev14:JKAr1brliXRVlugt@lonely-wheeler.ehdx9.mongodb.net/lonely-wheeler-db?retryWrites=true&w=majority"

}