/*
rule = DisableSymbols
DisableSymbols.symbols = [
  { symbol: "scala.Predef.println", message: "use logging instead" },
  "scala.Console.print",
  "scala.Predef.???",
  "java.lang.Throwable.printStackTrace"
]
*/
package fix

val MyConsole = Console

object DisableSymbolsScala3:

  import java.io.PrintWriter

  println("forbidden") // assert: DisableSymbols
  println("ok") // scalafix:ok; suppressed
  println() // assert: DisableSymbols
  Nil.foreach(println) // assert: DisableSymbols
  System.out.println("this is not forbidden")
  Console.println("this is not forbidden")
  Console.print("forbidden") // assert: DisableSymbols
  MyConsole.print("forbidden") // assert: DisableSymbols
  val x = ???
  new RuntimeException("forbidden").printStackTrace() // assert: DisableSymbols
  new RuntimeException("forbidden").printStackTrace(new PrintWriter("test.txt")) // assert: DisableSymbols
