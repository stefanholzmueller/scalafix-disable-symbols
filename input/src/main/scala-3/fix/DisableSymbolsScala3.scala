/*
rule = DisableSymbols
DisableSymbols.symbols = [
  "scala.Console.print",
  "scala.Predef.???"
]
*/
package fix
val MyConsole = Console

object DisableSymbolsScala3:
  MyConsole.print("forbidden") // assert: DisableSymbols
  // TODO val boom = ??? // assert: DisableSymbols
