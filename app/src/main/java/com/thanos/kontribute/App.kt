package com.thanos.kontribute

import android.app.Application
import co.paystack.android.PaystackSdk
import com.thanos.kontribute.di.component.AppComponent
import com.thanos.kontribute.di.module.AppModule
import com.thanos.kontribute.di.module.DependencyModule
import com.squareup.leakcanary.LeakCanary
import com.thanos.kontribute.di.component.DaggerAppComponent

class App: Application() {

    private lateinit var appComponent: AppComponent

    companion object {
        private lateinit var mInstance: App

        fun getInstance(): App = mInstance
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        PaystackSdk.initialize(this)
        PaystackSdk.setPublicKey(BuildConfig.paystack_public_key)
//        initLeakCanary()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .dependencyModule(DependencyModule())
                .build()
    }

    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

    fun getAppComponent(): AppComponent = appComponent

}
