package part5

import scala.util.Try

object DarkSugars extends App {


  // 1.


  // syntax sugar #1: methods with single param
  def singleArgMethod(arg: Int): String = s"$arg little ducks..."

  val description = singleArgMethod {
    // write some complex code
    42
  }


  //2.

  val aTryInstance = Try { // java's try {...}
    throw new RuntimeException
  }

  // 3.


  List(1, 2, 3).map { x =>
    x + 1
  }


  // 4.


  // syntax sugar #2: single abstract method
  trait Action {
    def act(x: Int): Int
  }

  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }

  val aFunkyInstance: Action = (x: Int) => x + 1 // magic

  // example: Runnables
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("hello, Scala")
  })

  val aSweeterThread = new Thread(() => println("sweet, Scala!"))


  // 5.

  class TeenGirl(name: String) {
    def `and then said`(gossip: String) = println(s"$name said $gossip")
  }

  val lilly = new TeenGirl("Lilly")
  lilly `and then said` "Scala is so sweet!"


  // 6.


  // syntax sugar #7: setters for mutable containers
  class Mutable {
    private var internalMember: Int = 0 // private for OO encapsulation
    def member = internalMember // "getter"
    def member_=(value: Int): Unit =
      internalMember = value // "setter"
  }

  val aMutableContainer = new Mutable
  aMutableContainer.member = 42 // rewrittern as aMutableContainer.member_=(42)


}
