/**
 * Created by anandwana001 on
 * 22, March, 2022
 **/
package com.akshay.homepage.di.module

import com.akshay.homepage.di.qualifier.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

/**
 * Provider for a [CoroutineScope].
 */
@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopeModule {

  @Singleton
  @Provides
  fun providesCoroutineScope(
    @IoDispatcher dispatcher: CoroutineDispatcher
  ): CoroutineScope = CoroutineScope(dispatcher)
}