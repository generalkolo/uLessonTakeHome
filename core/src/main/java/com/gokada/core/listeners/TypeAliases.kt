package com.gokada.core.listeners

typealias NoArgClickListener = () -> Unit

typealias SingleArgClickListener<T> = (value: T) -> Unit