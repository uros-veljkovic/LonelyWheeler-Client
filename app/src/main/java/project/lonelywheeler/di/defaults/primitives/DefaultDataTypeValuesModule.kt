package project.lonelywheeler.di.defaults.primitives

import android.graphics.Bitmap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import java.util.*
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultString

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultNullableString


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultLong

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultNullableLong

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultListOfLong

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultInt

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultNullableInt

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDouble

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultNullableDouble

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultNullableBitmap

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultBoolean

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDate


@InstallIn(ApplicationComponent::class)
@Module
class DefaultDataTypeValuesModule {

    @DefaultString
    @Singleton
    @Provides
    fun provideDefaultString(): String {
        return ""
    }

    @DefaultNullableString
    @Singleton
    @Provides
    fun provideDefaultNullableString(): String? {
        return null
    }

    @DefaultLong
    @Singleton
    @Provides
    fun provideDefaultLong(): Long {
        return 0L
    }

    @DefaultNullableLong
    @Singleton
    @Provides
    fun provideDefaultNullableLong(): Long? {
        return null
    }

    @DefaultInt
    @Singleton
    @Provides
    fun provideDefaultInt(): Int {
        return 0
    }

    @DefaultNullableInt
    @Singleton
    @Provides
    fun provideDefaultNullableInt(): Int? {
        return null
    }

    @DefaultDouble
    @Singleton
    @Provides
    fun provideDefaultDouble(): Double {
        return 0.0
    }

    @DefaultNullableInt
    @Singleton
    @Provides
    fun provideDefaultNullableDouble(): Double? {
        return null
    }

    @DefaultListOfLong
    @Singleton
    @Provides
    fun provideDefaultListOfLongs(): List<Long> {
        return arrayListOf()
    }

    @DefaultNullableBitmap
    @Singleton
    @Provides
    fun provideDefaultNullableBitmap(): Bitmap? {
        return null
    }

    @DefaultBoolean
    @Singleton
    @Provides
    fun provideDefaultBoolean(): Boolean {
        return false
    }

    @DefaultDate
    @Singleton
    @Provides
    fun provideDefaultDate(): Date {
        return Date()
    }
}