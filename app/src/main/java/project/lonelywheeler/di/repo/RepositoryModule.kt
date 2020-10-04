package project.lonelywheeler.di.repo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import project.lonelywheeler.db.service.ProductService
import project.lonelywheeler.db.service.UserService
import project.lonelywheeler.db.service.api.ProductApi
import project.lonelywheeler.db.service.api.UserApi
import project.lonelywheeler.db.repo.Repository
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
        productService: ProductService
    ): Repository {
        return Repository(userService, productService)
    }

    @Singleton
    @Provides
    fun provideProductService(
        productApi: ProductApi
    ): ProductService {
        return ProductService(productApi)
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
    fun provideProductApi(
        retrofit: Retrofit
    ): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    //TODO: umesto baseUrl uneti moju IP adresu (valjda)
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("OVDE MOJ URL")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}