package com.yahami.marvelcharacter.utils

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.applySchedulers(): Single<T> = this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}