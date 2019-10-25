package com.gokada.map.utils

import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest

fun autoCompleteRequest (block: FindAutocompletePredictionsRequest.Builder.() -> Unit):
        FindAutocompletePredictionsRequest
        = FindAutocompletePredictionsRequest.builder().apply(block).build()