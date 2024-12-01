package com.example.tuyasample.presentation

sealed class Router(val route: String) {
    object Dashboard: Router("Dashboard")
    object Document: Router("Document")
    object Scan: Router("Scan")
    object Peminjaman: Router("Peminjaman")
    object IOT: Router("IOT")

}