package org.jetbrains.kotlin.compiler.plugin.template

import org.jetbrains.kotlin.fir.declarations.FirFunction

object ArrowMeta {

    fun macro(scope: ArrowMetaScope.() -> Unit) {
        object : ArrowMetaScope {}.scope()
    }
}

interface ArrowMetaScope {

    fun function(description: String, predicate: FirFunction.() -> Boolean, registry: MacroContext.() -> Unit) {
        ArrowMetaMacros.macros[description] = { context ->
            if (context.declaration.predicate()) {
                context.registry()
            }
        }
    }
}