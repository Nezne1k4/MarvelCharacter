package com.yahami.marvelcharacter.data.remote.repository

/**
 * In the most common implementations, a concrete instance of MarvelRepository is passed to Presenter
 * as a constructor argument.
 *
 * But what about UI testing (such as Espresso tests)? We don't want to test the Marvel API and we
 * don't want to make a UI test depending on it.
 *
 * The solution is to make a mechanism that will generate a standard implementation during normal runtime,
 * but it will also allow us to set a different implementation for testing purposes.
 */
abstract class Provider<T> {

    abstract fun provides(): T

    private val instance: T by lazy { provides() }

    // for testing
    var testingInstance: T? = null

    /**
     * get Repository Instance
     */
    fun getsInstance(): T = testingInstance ?: instance
}