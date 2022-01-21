package com.kusch.filmsmvp

import com.github.terrakok.cicerone.Cicerone
import com.kusch.filmsmvp.data.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App> =
        DaggerApplicationComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()
                withNavigatorHolder(cicerone.getNavigatorHolder())
                withRouter(cicerone.router)
            }
            .build()
}