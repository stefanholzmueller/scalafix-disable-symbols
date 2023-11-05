package fix

import scalafix.lint.LintSeverity
import scalafix.v1._

import scala.meta._

class DisableSymbols extends SemanticRule("DisableSymbols") {

  private val disabledSymbol: SymbolMatcher = SymbolMatcher.normalized("scala.Predef.println")

  private case class DisabledSymbol(position: Position) extends Diagnostic {
    override def message: String = "Use loggers instead of println"
    override def severity: LintSeverity = LintSeverity.Error
  }

  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect { case disabledSymbol(t: Name) =>
      Patch.lint(DisabledSymbol(t.pos))
    }.asPatch
  }

}
