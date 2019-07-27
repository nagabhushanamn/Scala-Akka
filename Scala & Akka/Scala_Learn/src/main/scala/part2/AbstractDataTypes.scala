package part2

object AbstractDataTypes extends App {


  abstract class LT {
    def work: Unit
  }


  class Human extends LT {
    override def work: Unit = println("Human :: work")
  }


  trait Wheel {
    def rotate()
  }

  class MRFWheel extends Wheel {
    override def rotate(): Unit = println("MRF wheel rotating")
  }


  // traits vs abstract classes
  // 1 - traits do not have constructor parameters
  // 2 - multiple traits may be  inherited by the same class
  // 3 - traits = behavior, abstract class = "thing"


  // 3.
  // SOLID Principles


  // Ex.

  trait Friend {
    val name: String

    def listen = println("im " + name + " your Friend..")
  }

  class Person(val name: String) extends Friend


  val peter = new Person("peter")
  peter.listen


  def seekHelp(friend: Friend) = {
    friend.listen
  }

  seekHelp(peter)

  class Animal(val name: String)

  class Dog(name: String) extends Animal(name) with Friend

  class Cat(name: String) extends Animal(name)

  val rover = new Dog("Rover")

  seekHelp(rover)


  val snow = new Cat("Snow") with Friend
  snow.listen
  seekHelp(snow)




}
