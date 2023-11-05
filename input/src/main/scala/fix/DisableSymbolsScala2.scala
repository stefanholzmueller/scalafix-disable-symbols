/*
rule = DisableSymbols
DisableSymbols.symbols = [
  { symbol: "scala.Predef.println", message: "use logging instead" },
  "scala.Console.print",
  "java.lang.Throwable.printStackTrace",
  { symbol: "scala.Predef.???", message: "did you forget something?" }
]
 */
package fix

import java.io.PrintWriter

object DisableSymbolsScala2 {
  println("forbidden") // assert: DisableSymbols
  println("ok") // scalafix:ok; suppressed
  println() // assert: DisableSymbols
  Nil.foreach(println) /* assert: DisableSymbols
              ^^^^^^^
  use logging instead
   */
  System.out.println("this is not forbidden")
  Console.println("this is not forbidden")
  Console.print("forbidden") /* assert: DisableSymbols
          ^^^^^
  scala.Console.print is disabled by the DisableSymbols rule
   */
  new RuntimeException("forbidden").printStackTrace() // assert: DisableSymbols
  new RuntimeException("forbidden").printStackTrace(new PrintWriter("test.txt")) // assert: DisableSymbols
  // TODO
//  val x = ??? /* assert: DisableSymbols
//  ^^^
//  did you forget something ?
//  */
}
