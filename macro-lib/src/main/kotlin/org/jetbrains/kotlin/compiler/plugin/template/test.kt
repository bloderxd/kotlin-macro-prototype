package org.jetbrains.kotlin.compiler.plugin.template

import org.jetbrains.kotlin.diagnostics.error1
import org.jetbrains.kotlin.fir.declarations.utils.nameOrSpecialName
import org.jetbrains.kotlin.psi.KtElement

private val wrongFunctionName by error1<KtElement, String>()

@Macro
fun diagnosticFunctionMacro() {
    ArrowMeta.macro {
        function(
            description = "Report functions that contains 'bloder' in the name",
            predicate = { nameOrSpecialName.identifier.contains("bloder") }
        ) {
            declaration.report(
                wrongFunctionName,
                "Report Example"
            )
        }
    }
}