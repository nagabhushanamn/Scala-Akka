package part4

object PatternsEverywhere extends App {


  // big idea #1
  try {
    // code
  } catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }


  // catches are actually MATCHES
  /*
    try {
      // code
    } catch (e) {
      e match {
        case e: RuntimeException => "runtime"
        case npe: NullPointerException => "npe"
        case _ => "something else"
      }
    }
   */


  // big idea #2
  val list = List(1, 2, 3, 4)
  val evenOnes = for {
    x <- list if x % 2 == 0 // ?!
  } yield 10 * x


}
