package caatsoft.studio.com.gamecatalog.di

import caatsoft.studio.com.gamecatalog.MainViewModel
import caatsoft.studio.com.gamecatalog.network.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(Repository())
    }
}