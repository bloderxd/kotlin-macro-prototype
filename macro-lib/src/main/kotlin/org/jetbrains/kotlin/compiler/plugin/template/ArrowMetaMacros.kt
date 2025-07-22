package org.jetbrains.kotlin.compiler.plugin.template

object ArrowMetaMacros {

    val macros: MutableMap<String, (MacroContext) -> Unit> = mutableMapOf()
}