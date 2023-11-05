package fix

import metaconfig.Configured
import scalafix.v1._
import scala.meta._

object DisableSymbols {
  private val name = "DisableSymbols"
}

class DisableSymbols(config: DisableSymbolsConfig) extends SemanticRule(DisableSymbols.name) {
  def this() = this(DisableSymbolsConfig())

  override def withConfiguration(config: Configuration): Configured[Rule] =
    config.conf
      .getOrElse(DisableSymbols.name)(this.config)
      .map { newConfig => new DisableSymbols(newConfig) }

  override def fix(implicit doc: SemanticDocument): Patch = {
    config.symbols.flatMap { cfg =>
      val matcher = SymbolMatcher.normalized(cfg.value)
      doc.tree.collect { case matcher(t: Name) =>
        val diagnostic = Diagnostic(
          id = "",
          message = cfg.message.getOrElse(s"${cfg.value} is disabled by the ${DisableSymbols.name} rule"),
          position = t.pos
        )
        Patch.lint(diagnostic)
      }
    }.asPatch
  }

}
