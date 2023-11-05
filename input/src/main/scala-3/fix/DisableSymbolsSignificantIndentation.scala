/*
rule = DisableSymbols
DisableSymbols.symbols = [
  "scala.Predef.println"
]
*/
package fix

object DisableSymbolsSignificantIndentation:
  println("forbidden") // assert: DisableSymbols
  println("ok") // scalafix:ok; suppressed
  println() // assert: DisableSymbols
  Nil.foreach(println) // assert: DisableSymbols
  System.out.println("this is not forbidden")
