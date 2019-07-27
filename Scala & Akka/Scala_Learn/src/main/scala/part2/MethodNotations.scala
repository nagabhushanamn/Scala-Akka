package part2

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {

    def likes(movie: String): Boolean = movie == favoriteMovie

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"

    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)


    def unary_! : String = s"$name, what the heck?!"

    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)


    def isAlive: Boolean = true

    def learns(thing: String) = s"$name is learning $thing"

    def learnsScala = this learns "Scala"

    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    def apply(n: Int): String = s"$name watched $favoriteMovie $n times"
  }


  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // infix notation = operator notation (syntactic sugar)


  // "operators" in Scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))
  println((mary + "rockstar").name)


  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS.
  // Akka actors have ! ?


  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !


  // postfix notation
  import scala.language.postfixOps

  println(mary.isAlive)
  println(mary isAlive)


  println(mary learns "scala")
  println(mary learnsScala)


  // apply
  println(mary.apply())
  println(mary()) // equivalent





  // Ex.

  class Car {
    def turn(direction: String) = {
      println("turning " + direction)
    }
  }

  val car = new Car
  car.turn("left")
  car turn ("left")
  car turn "left"




}
