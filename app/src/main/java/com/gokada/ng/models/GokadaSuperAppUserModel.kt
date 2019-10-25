package com.gokada.ng.models

sealed class GokadaSuperAppUserModel {

    data class GokadaRiderModel (
        val id: String,
        val firstName: String,
        val lastName: String,
        val profileImage: String,
        val email: String?
    ): GokadaSuperAppUserModel()

    data class GokadaPilotModel (
        val id: String,
        val firstName: String,
        val lastName: String,
        val profileImage: String,
        val email: String?
    ): GokadaSuperAppUserModel()
}