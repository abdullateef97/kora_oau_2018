package com.thanos.kontribute.di.component

import com.thanos.kontribute.di.module.AppModule
import com.thanos.kontribute.di.module.DependencyModule
import com.thanos.kontribute.ui.home.HomeFragment
import com.thanos.kontribute.ui.login.LoginActivity
import com.thanos.kontribute.ui.main.MainActivity
import com.thanos.kontribute.ui.profile.ProfileFragment
import com.thanos.kontribute.ui.register.RegisterActivity
import com.thanos.kontribute.ui.transactions.TransactionsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (DependencyModule::class)])
interface AppComponent {
    fun inject(registerActivity: RegisterActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(profileFragment: ProfileFragment)
    fun inject(homeFragment: HomeFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(transactionsFragment: TransactionsFragment)


}