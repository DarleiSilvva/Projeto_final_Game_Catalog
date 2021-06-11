package caatsoft.studio.com.gamecatalog.di

import caatsoft.studio.com.gamecatalog.viewmodel.FavoriteViewModel
import caatsoft.studio.com.gamecatalog.viewmodel.GamesViewModel
import caatsoft.studio.com.gamecatalog.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        GamesViewModel(repository = get(), favoriteRepository = get())
    }
    viewModel {
        FavoriteViewModel(favoriteRepository = get())
    }
    viewModel {
        UserViewModel()
    }
}