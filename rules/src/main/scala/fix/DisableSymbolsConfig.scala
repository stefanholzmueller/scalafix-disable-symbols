package fix

import metaconfig.ConfDecoder
import metaconfig.generic.Surface
import scalafix.config.CustomMessage

case class DisableSymbolsConfig(
    symbols: Seq[CustomMessage[String]] = Nil
)

object DisableSymbolsConfig {
  def default = DisableSymbolsConfig()
  implicit val surface: Surface[DisableSymbolsConfig] = metaconfig.generic.deriveSurface[DisableSymbolsConfig]
  implicit val stringDecoder: ConfDecoder[CustomMessage[String]] = CustomMessage.decoder[String]("symbol")
  implicit val decoder: ConfDecoder[DisableSymbolsConfig] = metaconfig.generic.deriveDecoder(default)
}
