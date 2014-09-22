package io.continuum.bokeh

import scala.annotation.StaticAnnotation
import scala.reflect.macros.Context

private object Model {
    def macroTransformImpl(c: Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
        import c.universe._

        annottees.map(_.tree) match {
            case ClassDef(mods, name, tparams, tpl @ Template(parents, sf, body)) :: companion =>
                val method = q"""
                    override def values: List[(String, Option[play.api.libs.json.JsValue])] = {
                        io.continuum.bokeh.Fields.values(this)
                    }
                """
                val decl = ClassDef(mods, name, tparams, Template(parents, sf, body :+ method))
                c.Expr[Any](Block(decl :: companion, Literal(Constant(()))))
            case _ => c.abort(c.enclosingPosition, "expected a class")
        }
    }
}

class model extends StaticAnnotation {
    def macroTransform(annottees: Any*): Any = macro Model.macroTransformImpl
}