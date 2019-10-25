package com.gokada.ng.mappers

interface PresentationMapper <PRESENTATION, DOMAIN> {

    fun mapToPresentation(domain: DOMAIN): PRESENTATION

    fun mapToDomain(presentation: PRESENTATION): DOMAIN

    fun mapToDomainList (presentations: List<PRESENTATION>) = presentations.map { mapToDomain(it) }

    fun mapToPresentationList(domains: List<DOMAIN>) = domains.map { mapToPresentation(it) }
}