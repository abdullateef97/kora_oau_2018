package com.thanos.kontribute.di.module

import com.atlascc.kontribute.data.remote.ApiService
import com.thanos.kontribute.ui.home.HomePresenter
import com.thanos.kontribute.ui.transactions.TransactionsPresenter
import com.thanos.kontribute.ui.login.LoginPresenter
import com.thanos.kontribute.ui.profile.ProfilePresenter
import com.thanos.kontribute.ui.register.RegisterPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DependencyModule {


    @Provides
    @Singleton
    fun provideRegisterPresenter(apiService: ApiService): RegisterPresenter {
        return RegisterPresenter(apiService)
    }

    @Provides
    @Singleton
    fun provideLoginPresenter(apiService: ApiService): LoginPresenter {
        return LoginPresenter(apiService)
    }

    @Provides
    @Singleton
    fun provideProfilePresenter(apiService: ApiService): ProfilePresenter {
        return ProfilePresenter(apiService)
    }

    @Provides
    @Singleton
    fun provideHomePresenter(): HomePresenter {
        return HomePresenter()
    }

    @Provides
    @Singleton
    fun provideTransactionsPresenter(): TransactionsPresenter {
        return TransactionsPresenter()
    }

}