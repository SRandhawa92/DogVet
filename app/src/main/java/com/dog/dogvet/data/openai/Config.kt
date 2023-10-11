package com.dog.dogvet.data.openai

object Config {
    const val AI_ENGINE = "gpt-3.5-turbo"
    const val MAX_TOKENS = 200
    private const val ROLE = "assistant"
    private const val PROMPT = "You are a veterinary assistant who helps children diagnose issues with their pets"
    val promptMsg = Message(
        role = ROLE,
        content = PROMPT,
    )
}
