package eu.timepit.crjdt

import eu.timepit.crjdt.Cmd._
import eu.timepit.crjdt.Expr._
import eu.timepit.crjdt.Val._

object syntax {
  final class LetSyntax {
    def update(x: Var, expr: Expr): Cmd = Let(x, expr)
  }

  val doc: Expr = Doc
  val let: LetSyntax = new LetSyntax
  def v(name: String): Var = Var(name)
  val `{}`: Val = EmptyMap
  val `[]`: Val = EmptyList

  implicit final class CmdOps(val self: Cmd) extends AnyVal {
    def `;`(cmd2: Cmd): Cmd = Sequence(self, cmd2)
  }

  implicit final class ExprOps(val self: Expr) extends AnyVal {
    def :=(value: Val): Cmd = Assign(self, value)
    def insert(value: Val): Cmd = Insert(self, value)
    def delete: Cmd = Delete(self)

    def downField(key: String): Expr = DownField(self, key)
    def iter: Expr = Iter(self)
    def next: Expr = Next(self)
  }

  implicit def string2Val(s: String): Val = Str(s)
}