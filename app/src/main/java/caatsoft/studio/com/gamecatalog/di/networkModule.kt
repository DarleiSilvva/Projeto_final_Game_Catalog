package caatsoft.studio.com.gamecatalog.di

import caatsoft.studio.com.gamecatalog.BuildConfig
import caatsoft.studio.com.gamecatalog.network.GameAPIInterface
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single(named("base_url")) {
        "https://www.giantbomb.com/api/"
    }
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        interceptor
    }
    single {
        val client = OkHttpClient().newBuilder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            client.addInterceptor(get<HttpLoggingInterceptor>())
        }
        client.build()
    }
    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single {
        Retrofit.Builder().baseUrl(get<String>(named("base_url")))
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
    }

    /*single {
        Retrofit.Builder().baseUrl(get<String>(named("base_url")))
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }*/


    single {
        get<Retrofit>().create(GameAPIInterface::class.java)
    }

}