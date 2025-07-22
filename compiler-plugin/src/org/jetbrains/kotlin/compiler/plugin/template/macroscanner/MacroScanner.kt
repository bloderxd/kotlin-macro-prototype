package org.jetbrains.kotlin.compiler.plugin.template.macroscanner

import io.github.classgraph.ClassGraph
import org.jetbrains.kotlin.compiler.plugin.template.Macro

internal object MacroScanner {

    fun findAllMacroAnnotatedMethods() {
        val scanResult = ClassGraph()
            .enableClassInfo()
            .enableMethodInfo()
            .enableAnnotationInfo()
            .acceptPackages("org.jetbrains.kotlin.compiler.plugin.template") // adjust this
            .scan()

        val classes = scanResult.getClassesWithMethodAnnotation(Macro::class.java)
        for (classInfo in classes) {
            val clazz = classInfo.loadClass()
            val methodInfos = classInfo.methodInfo
            for (methodInfo in methodInfos) {
                if (methodInfo.annotationInfo.any { it.name == "org.jetbrains.kotlin.compiler.plugin.template.Macro" }) {
                    val parameterClasses = methodInfo.parameterInfo.map { paramInfo ->
                        Class.forName(paramInfo.typeDescriptor.javaClass.name)
                    }.toTypedArray()

                    clazz.getDeclaredMethod(methodInfo.name, *parameterClasses).invoke(this)
                }
            }
        }

        scanResult.close()
    }
}