//@
package xyz.hyperreal.bvm

import scala.util.parsing.input.Position

object Main extends App {

  val constants =
    Map(
      "write" -> { (_: VM, apos: Position, ps: List[Position], args: Any) =>
        val list =
          args match {
            case a: ArgList => a.array toList
            case a          => List(a)
          }

        println(list map (a => display(deref(a))) mkString ", ")
      }
    )

  val program =
    SourceAST(
      List(
        ApplyExpressionAST(
          null,
          VariableExpressionAST(null, "write", "write"),
          null,
          List(
            (null,
             ScanExpressionAST(null, LiteralExpressionAST("asdf"), MatchExpressionAST(LiteralExpressionAST("a")))),
          ),
          false
        )
        //, AssignmentExpressionAST( List((null, VariableExpressionAST(null, "a", "a"))), '=, null, List((null, LiteralExpressionAST( "bye bye again" ))) )
      ))

  run(program, constants, Map(), Map(), null)
}
