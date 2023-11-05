/*
rule = DisableSymbols
*/
package fix

object DisableSymbolsSignificantIndentation:
  println() // assert: DisableSymbols
  println("") // assert: DisableSymbols
  println("ok") // scalafix:ok; suppressed
  Nil.foreach(println) // assert: DisableSymbols
