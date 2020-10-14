package project.lonelywheeler.di.defaults.primitives

import android.graphics.Bitmap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ApplicationComponent
import java.util.*
import javax.inject.Qualifier

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
annotation class DefaultNullableBitmap

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultBoolean

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDate


@InstallIn(ActivityRetainedComponent::class)
@Module
class DefaultDataTypeValuesModule {

    @DefaultString
    @Provides
    fun provideDefaultString(): String {
        return ""
    }

    @DefaultNullableString
    @Provides
    fun provideDefaultNullableString(): String? {
        return ""
    }

    @DefaultLong
    @Provides
    fun provideDefaultLong(): Long {
        return 0L
    }

    @DefaultNullableLong
    @Provides
    fun provideDefaultNullableLong(): Long? {
        return 0L
    }

    @DefaultInt
    @Provides
    fun provideDefaultInt(): Int {
        return 0
    }

    @DefaultNullableInt
    @Provides
    fun provideDefaultNullableInt(): Int? {
        return 0
    }

    @DefaultDouble
    @Provides
    fun provideDefaultDouble(): Double {
        return 0.0
    }

    @DefaultNullableInt
    @Provides
    fun provideDefaultNullableDouble(): Double? {
        return 0.0
    }

    @DefaultListOfLong
    @Provides
    fun provideDefaultListOfLongs(): List<Long> {
        return arrayListOf()
    }

    @DefaultNullableBitmap
    @Provides
    fun provideDefaultNullableBitmap(): Bitmap? {
        return null
    }

    @DefaultBoolean
    @Provides
    fun provideDefaultBoolean(): Boolean {
        return false
    }

    @DefaultDate
    @Provides
    fun provideDefaultDate(): Date {
        return Date()
    }
}