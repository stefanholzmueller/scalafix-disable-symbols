package fix

import metaconfig.ConfDecoder
import metaconfig.generic.Surface

case class DisableSymbolsConfig(
    symbols: List[String] = List.empty
)

object DisableSymbolsConfig {
  def default = DisableSymbolsConfig()
  implicit val surface: Surface[DisableSymbolsConfig] = metaconfig.generic.deriveSurface[DisableSymbolsConfig]
  implicit val decoder: ConfDecoder[DisableSymbolsConfig] = metaconfig.generic.deriveDecoder(default)
}
