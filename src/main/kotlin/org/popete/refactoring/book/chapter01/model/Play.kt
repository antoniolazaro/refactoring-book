package org.popete.refactoring.book.chapter01.model

data class Plays(val plays: MutableMap<String, Play>) {

    init {
        plays["hamlet"] = Play("Hamlet","tragedy")
        plays["asYouLike"] = Play("As you like it","comedy")
        plays["othelo"] = Play("Othelo","tragedy")
    }
}

data class Play(val name: String, val type: String)
