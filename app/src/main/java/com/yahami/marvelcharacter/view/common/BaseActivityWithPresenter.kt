package com.yahami.marvelcharacter.view.common

import android.support.v7.app.AppCompatActivity
import com.yahami.marvelcharacter.presenter.BasePresenterImp

abstract class BaseActivityWithPresenter : AppCompatActivity() {
    abstract val presenter: BasePresenterImp

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}