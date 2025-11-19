package com.emperormoh.reviveapp.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UnauthenticatedClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthenticatedClient