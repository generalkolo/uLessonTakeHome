package com.ulesson.core.utils.state

class AppWrapperResource<out D> constructor(
    val state: AppState,
    val message: String? = null,
    val data: D? = null
) {
    companion object {
        @JvmStatic
        fun <D> success(
            data: D? = null,
            message: String? = null
        ): AppWrapperResource<D> = AppWrapperResource(
            state = AppState.SUCCESS,
            data = data,
            message = message
        )

        @JvmStatic
        fun <D> warning(
            message: String? = null
        ): AppWrapperResource<D> = AppWrapperResource(
            state = AppState.WARNING,
            message = message
        )

        @JvmStatic
        fun <D> validationFailed(
            message: String? = null
        ): AppWrapperResource<D> = AppWrapperResource(
            state = AppState.VALIDATION_FAILED,
            message = message
        )

        @JvmStatic
        fun <D> failed(
            message: String?
        ): AppWrapperResource<D> = AppWrapperResource(
            state = AppState.FAILED,
            data = null,
            message = message
        )

        @JvmStatic
        fun <D> loading(): AppWrapperResource<D> = AppWrapperResource(
            state = AppState.LOADING,
            data = null,
            message = null
        )
    }
}
