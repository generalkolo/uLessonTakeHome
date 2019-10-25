package com.gokada.ng.di.modules.repository

import com.gokada.domain.repository.CancelReasonsRepository
import com.gokada.domain.repository.IDriverDetailsRepository
import com.gokada.domain.repository.IPaymentRepository
import com.gokada.domain.repository.RidersRepository
import com.gokada.ng.di.scopes.GokadaSuperAppScope
import com.gokada.repository.impl.samples.CancelReasonsRepositoryImpl
import com.gokada.repository.impl.samples.ManualRideRepositoryImpl
import com.gokada.repository.impl.samples.PaymentRepositoryImpl
import com.gokada.repository.impl.samples.RiderRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class GokadaSuperAppRepositoryModule {

    @Binds
    @GokadaSuperAppScope
    internal abstract fun bindDriverDetailsRepository(
        repository: ManualRideRepositoryImpl
    ): IDriverDetailsRepository

    @Binds
    @GokadaSuperAppScope
    internal abstract fun bindCancelReasonsRepository(
        repository: CancelReasonsRepositoryImpl
    ): CancelReasonsRepository

    @Binds
    @GokadaSuperAppScope
    internal abstract fun bindRiderRepository(
        repository: RiderRepositoryImpl
    ): RidersRepository

    @Binds
    @GokadaSuperAppScope
    internal abstract fun bindPaymentRepository(
        repository: PaymentRepositoryImpl
    ): IPaymentRepository

}