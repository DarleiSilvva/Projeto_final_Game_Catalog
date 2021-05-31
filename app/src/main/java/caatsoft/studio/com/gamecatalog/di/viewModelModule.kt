package caatsoft.studio.com.gamecatalog.di

import com.test.testtwo.viewmodel.GamesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        GamesViewModel(repository = get())
    }
}