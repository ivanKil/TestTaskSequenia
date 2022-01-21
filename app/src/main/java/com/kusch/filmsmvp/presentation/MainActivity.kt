package com.kusch.filmsmvp.presentation

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.kusch.filmsmvp.R
import com.kusch.filmsmvp.databinding.ActivityMainBinding
import com.kusch.filmsmvp.presentation.abs.AbsActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AbsActivity(), MainView {
    private val navigator = AppNavigator(this, R.id.container)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var presenterProvider: Provider<MainPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    private val vb: ActivityMainBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}