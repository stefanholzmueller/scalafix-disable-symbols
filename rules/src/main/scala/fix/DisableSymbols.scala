package fix

import metaconfig.Configured
import scalafix.lint.LintSeverity
import scalafix.v1._

import scala.meta._

class DisableSymbols(config: DisableSymbolsConfig) extends SemanticRule("DisableSymbols") {
  def this() = this(DisableSymbolsConfig())

  override def withConfiguration(config: Configuration): Configured[Rule] =
    config.conf
      .getOrElse("DisableSymbols")(this.config)
      .map { newConfig => new DisableSymbols(newConfig) }

  private case class DisabledSymbol(position: Position) extends Diagnostic {
    override def message: String = "Use loggers instead of println"
    override def severity: LintSeverity = LintSeverity.Error
  }

  override def fix(implicit doc: SemanticDocument): Patch = {
    config.symbols.flatMap { symbol =>
      val matcher = SymbolMatcher.normalized(symbol)
      doc.tree.collect { case matcher(t: Name) =>
        Patch.lint(DisabledSymbol(t.pos))
      }
    }.asPatch
  }

}
