package org.popete.refactoring.book.chapter01.model

data class Invoices(val customer: String){
    val performances = mutableListOf(Invoice("hamlet",55),Invoice("asYouLike",35),Invoice("othelo",40))
}

data class Invoice(val playId: String, val audience: Int)
