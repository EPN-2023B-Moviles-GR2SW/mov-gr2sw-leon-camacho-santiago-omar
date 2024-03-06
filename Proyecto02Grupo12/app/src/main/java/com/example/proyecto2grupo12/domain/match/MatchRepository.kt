package com.example.proyecto2grupo12.domain.match

import com.example.proyecto2grupo12.domain.match.entity.Match

interface MatchRepository {
    suspend fun getMatches(): List<Match>
}