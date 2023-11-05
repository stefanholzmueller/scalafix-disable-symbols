/*
rule = DisableSymbols
DisableSymbols.symbols = [
  { symbol: "scala.Predef.println", message: "use logging instead" },
  "scala.Console.print"
]
 */
package fix

object DisableSymbols {
  println("forbidden") // assert: DisableSymbols
  println("ok") // scalafix:ok; suppressed
  println() // assert: DisableSymbols
  Nil.foreach(println) // assert: DisableSymbols
  System.out.println("this is not forbidden")
  Console.println("this is not forbidden")
  Console.print("forbidden") // assert: DisableSymbols
  ??? // not forbidden
}
