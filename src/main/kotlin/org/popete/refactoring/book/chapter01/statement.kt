package org.popete.refactoring.book.chapter01

import org.popete.refactoring.book.chapter01.model.Invoice
import org.popete.refactoring.book.chapter01.model.Invoices
import org.popete.refactoring.book.chapter01.model.Play
import org.popete.refactoring.book.chapter01.model.Plays
import kotlin.math.floor

fun main() {

    val invoices = Invoices("Demo")
    val plays = Plays(mutableMapOf())
    println(statement(invoices, plays ))
}

fun statement(invoice: Invoices, plays: Plays) : String{
   var result = "Statement for ${invoice.customer}\n"
    for (performance in invoice.performances){
        val play = playFor(plays, performance)
        result += "${play?.name}: ${amountFor(play, performance)/100} (${performance.audience} seats)\n"
    }
    result += "Amount owed is ($${totalAmount(invoice, plays)/100})\n"
    result += "You earned ${totalVolumeCredits(invoice, plays)} credits\n"
    return result
}

private fun totalAmount(
    invoice: Invoices,
    plays: Plays
): Int {
    var result = 0
    for (performance in invoice.performances) {
        result += amountFor(playFor(plays, performance), performance)
    }
    return result
}

private fun totalVolumeCredits(
    invoice: Invoices,
    plays: Plays
): Double {
    var volumeCredits = 0.0
    for (performance in invoice.performances) {
        volumeCredits += volumeCreditsFor(performance, playFor(plays, performance))
    }
    return volumeCredits
}

private fun volumeCreditsFor(
    aPerformance: Invoice,
    aPlay: Play?
): Double {
    var result = (aPerformance.audience - 30).coerceAtLeast(0).toDouble()
    if ("comedy" == aPlay?.type) {
        result += floor((aPerformance.audience / 5).toDouble())
    }
    return result
}

private fun playFor(
    plays: Plays,
    aPerformance: Invoice
) = plays.plays[aPerformance.playId]

private fun amountFor(
    aPlay: Play?,
    aPerformance: Invoice
): Int {
    var result: Int
    when (aPlay?.type) {
        "tragedy" -> {
            result = 40000
            if (aPerformance.audience > 30) {
                result += 1000 * (aPerformance.audience - 30)
            }
        }
        "comedy" -> {
            result = 30000
            if (aPerformance.audience > 20) {
                result += 10000 + 500 * (aPerformance.audience - 20)
            }
            result += 300 * aPerformance.audience
        }
        else -> throw Exception("unkown type ${aPlay?.type}")
    }
    return result
}