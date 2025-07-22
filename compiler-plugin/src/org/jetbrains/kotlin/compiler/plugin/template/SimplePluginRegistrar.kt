package org.jetbrains.kotlin.compiler.plugin.template

import org.jetbrains.kotlin.compiler.plugin.template.fir.MacroFirExample
import org.jetbrains.kotlin.compiler.plugin.template.fir.SimpleClassGenerator
import org.jetbrains.kotlin.compiler.plugin.template.macroscanner.MacroScanner
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class SimplePluginRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +{ session: FirSession ->
            MacroFirExample(session)
        }
        +::SimpleClassGenerator
    }
}
