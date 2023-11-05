/*
rule = DisableSymbols
DisableSymbols.symbols = [
  { symbol: "scala.Predef.println", message: "use logging instead" }
]
*/
package fix

object DisableSymbolsScala3:
  println("forbidden") // assert: DisableSymbols
  println("ok") // scalafix:ok; suppressed
  println() // assert: DisableSymbols
  Nil.foreach(println) // assert: DisableSymbols
  System.out.println("this is not forbidden")
