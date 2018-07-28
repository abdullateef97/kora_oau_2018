package com.thanos.kontribute.di.component

import com.thanos.kontribute.di.module.AppModule
import com.thanos.kontribute.di.module.DependencyModule
import com.thanos.kontribute.ui.create_group.CreateGroupActivity
import com.thanos.kontribute.ui.deposit.DepositActivity
import com.thanos.kontribute.ui.group_detail.AddMemberActivity
import com.thanos.kontribute.ui.group_detail.GroupDetailActivity
import com.thanos.kontribute.ui.group_detail.members.MembersFragment
import com.thanos.kontribute.ui.group_detail.pay_ins.PayInsFragment
import com.thanos.kontribute.ui.group_detail.pay_outs.PayOutsFragment
import com.thanos.kontribute.ui.home.HomeFragment
import com.thanos.kontribute.ui.login.LoginActivity
import com.thanos.kontribute.ui.main.MainActivity
import com.thanos.kontribute.ui.profile.ProfileFragment
import com.thanos.kontribute.ui.register.RegisterActivity
import com.thanos.kontribute.ui.splash.SplashActivity
import com.thanos.kontribute.ui.transactions.TransactionsFragment
import com.thanos.kontribute.ui.withdrawal.WithdrawalActivity
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
    fun inject(createGroupActivity: CreateGroupActivity)
    fun inject(depositActivity: DepositActivity)
    fun inject(withdrawalActivity: WithdrawalActivity)
    fun inject(addMemberActivity: AddMemberActivity)


    fun inject(membersFragment: MembersFragment)
    fun inject(payInsFragment: PayInsFragment)
    fun inject(payOutsFragment: PayOutsFragment)
    fun inject(groupDetailActivity: GroupDetailActivity)
    fun inject(splashActivity: SplashActivity)
}