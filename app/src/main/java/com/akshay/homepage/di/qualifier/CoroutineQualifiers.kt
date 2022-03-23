/**
 * Created by anandwana001 on
 * 22, March, 2022
 **/
package com.akshay.homepage.di.qualifier

import javax.inject.Qualifier

/**
 * Qualifiers for all three type of [CoroutineDispatcher]
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher