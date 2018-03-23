package com.yahami.marvelcharacter.presenter

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenterImp : BasePresenter {
    protected var subscriptions = CompositeDisposable()

    override fun onViewDestroyed() {
        subscriptions.dispose()
    }
}