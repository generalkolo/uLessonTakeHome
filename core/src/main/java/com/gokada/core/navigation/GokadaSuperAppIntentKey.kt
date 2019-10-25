package com.gokada.core.navigation

sealed class GokadaSuperAppIntentKey {

    class Authentication : GokadaSuperAppIntentKey()
    class Main : GokadaSuperAppIntentKey()
    class DashBoard : GokadaSuperAppIntentKey()
}