package com.thanos.kontribute.di.module

import com.atlascc.kontribute.data.remote.ApiService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.thanos.kontribute.ui.create_group.CreateGroupPresenter
import com.thanos.kontribute.ui.group_detail.members.MembersPresenter
import com.thanos.kontribute.ui.group_detail.pay_ins.PayInsPresenter
import com.thanos.kontribute.ui.group_detail.pay_outs.PayOutsPresenter
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
    fun provideRegisterPresenter(firebaseAuth: FirebaseAuth, firestore: FirebaseFirestore):
            RegisterPresenter {
        return RegisterPresenter(firebaseAuth, firestore)
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

    @Provides
    @Singleton
    fun provideCreateGroupPresenter(): CreateGroupPresenter {
        return CreateGroupPresenter()
    }

    @Provides
    @Singleton
    fun provideMembersPresenter(): MembersPresenter {
        return MembersPresenter()
    }

    @Provides
    @Singleton
    fun providePayInsPresenter(): PayInsPresenter {
        return PayInsPresenter()
    }

    @Provides
    @Singleton
    fun providePayOutsPresenter(): PayOutsPresenter {
        return PayOutsPresenter()
    }
}