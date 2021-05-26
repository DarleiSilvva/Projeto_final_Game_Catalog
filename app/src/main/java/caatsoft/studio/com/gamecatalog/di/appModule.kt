package caatsoft.studio.com.gamecatalog.di

import caatsoft.studio.com.gamecatalog.ActivityRetriever
import caatsoft.studio.com.gamecatalog.DefaultCurrentActivityListener
import org.koin.dsl.module

val appModule = module {
    single { DefaultCurrentActivityListener() }
    single { ActivityRetriever(get()) }
}