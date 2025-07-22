package org.jetbrains.kotlin.compiler.plugin.template.fir

import org.jetbrains.kotlin.compiler.plugin.template.ArrowMetaMacros
import org.jetbrains.kotlin.compiler.plugin.template.MacroContext
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirFunctionChecker
import org.jetbrains.kotlin.fir.analysis.extensions.FirAdditionalCheckersExtension
import org.jetbrains.kotlin.fir.declarations.FirFunction

class MacroFirExample(
    session: FirSession
) : FirAdditionalCheckersExtension(session) {

    override val declarationCheckers: DeclarationCheckers = object : DeclarationCheckers() {
        override val functionCheckers: Set<FirFunctionChecker> = setOf(
            object : FirFunctionChecker(mppKind = MppCheckerKind.Common) {
                override fun check(
                    declaration: FirFunction,
                    context: CheckerContext,
                    reporter: DiagnosticReporter
                ) {
                    val context = MacroContext(declaration, reporter, context)
                    ArrowMetaMacros.macros.values.forEach { macro ->
                        macro(context)
                    }
                }
            }
        )
    }
}