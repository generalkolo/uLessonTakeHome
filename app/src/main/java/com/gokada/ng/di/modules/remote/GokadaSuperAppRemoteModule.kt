package com.gokada.ng.di.modules.remote

import com.gokada.local.impl.auth.GokadaAuthenticationLocalImpl
import com.gokada.ng.di.scopes.GokadaSuperAppScope
import com.gokada.remote.impl.samples.CancelReasonRemoteImpl
import com.gokada.remote.impl.samples.ManualRideRemoteImpl
import com.gokada.remote.impl.samples.PaymentRemoteImpl
import com.gokada.remote.impl.samples.RiderRemoteImpl
import com.gokada.repository.local.auth.IAuthenticationLocal
import com.gokada.repository.remote.sample.CancelReasonsRemote
import com.gokada.repository.remote.sample.IPaymentRemote
import com.gokada.repository.remote.sample.IRiderRemote
import com.gokada.repository.remote.sample.ManualRideRemote
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        GokadaSuperAppRetrofitModule::class
    ]
)
abstract class GokadaSuperAppRemoteModule {

    @Binds
    @GokadaSuperAppScope
    internal abstract fun bindAuthLocalRemote(
        remote: GokadaAuthenticationLocalImpl
    ): IAuthenticationLocal

    @Binds
    @GokadaSuperAppScope
    internal abstract fun bindDriverRemote(
        remote: ManualRideRemoteImpl
    ): ManualRideRemote

    @Binds
    @GokadaSuperAppScope
    internal abstract fun bindCancelReasonsRemote(
        remote: CancelReasonRemoteImpl
    ): CancelReasonsRemote

    @Binds
    @GokadaSuperAppScope
    internal abstract fun bindRiderRemote(
        remote: RiderRemoteImpl
    ): IRiderRemote

    @Binds
    @GokadaSuperAppScope
    internal abstract fun bindPaymentRemote(
        remote: PaymentRemoteImpl
    ): IPaymentRemote

}