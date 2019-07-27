package part5

object PlayGround extends App {

  var pf: PartialFunction[Any, Unit] = {
    case str: String => println("i received string..")
    case num: Int => println("i received number..")
  }

  pf("string")
  pf(123)


  //----------------------------------------------------

  //implicits

  implicit val company = "siemens"

  def greet(emp: String)(implicit company: String) = {
    println(s"hello $emp - $company")
  }

  greet("Nag")


}
