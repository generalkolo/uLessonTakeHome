package com.gokada.core.utils.state

class GokadaSuperAppResource <out D> constructor(
    val state: GokadaSuperAppState,
    val message: String? = null,
    val data: D? = null
){
    companion object {
        @JvmStatic
        fun <D> success(
            data: D? = null,
            message: String? = null
        ): GokadaSuperAppResource<D> = GokadaSuperAppResource(
            state = GokadaSuperAppState.SUCCESS,
            data = data,
            message = message
        )

        @JvmStatic
        fun <D> warning(
            message: String? = null
        ): GokadaSuperAppResource<D> = GokadaSuperAppResource(
            state = GokadaSuperAppState.WARNING,
            message = message
        )

        @JvmStatic
        fun <D> validationFailed(
            message: String? = null
        ): GokadaSuperAppResource<D> = GokadaSuperAppResource(
            state = GokadaSuperAppState.VALIDATION_FAILED,
            message = message
        )

        @JvmStatic
        fun <D> failed(
            message: String?
        ): GokadaSuperAppResource<D> = GokadaSuperAppResource(
            state = GokadaSuperAppState.FAILED,
            data = null,
            message = message
        )

        @JvmStatic
        fun <D> loading(): GokadaSuperAppResource<D> = GokadaSuperAppResource(
            state = GokadaSuperAppState.LOADING,
            data = null,
            message = null
        )

        @JvmStatic
        fun <D> loadingMore(): GokadaSuperAppResource<D> = GokadaSuperAppResource(
            state = GokadaSuperAppState.LOADING_MORE,
            data = null,
            message = null
        )
    }
}