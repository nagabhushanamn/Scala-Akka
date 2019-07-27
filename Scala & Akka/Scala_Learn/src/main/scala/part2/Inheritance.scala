package part2

object Inheritance extends App {

  // 1.
  class Employee(val name: String, val age: Int, val salary: Double) {
    val company = "XXX";
    def this(name: String) = this(name, 0, 0.0)
  }

  class TechEmployee(name: String, age: Int, salary: Double, val skill: String = "coding") extends Employee(name) {
    override val company: String = "YYY"
  }


  val techEmployee = new TechEmployee("Nag", 36, 1000.00)




  // 2.
  class LT {
    def eat() = println("LT :: eat()")
    def sleep() = println("LT :: sleep()")
    //def work() = println("LT :: work()")
    def work() = println("LT :: work")
  }


  sealed class Human extends LT {
    def study() = println("Human :: stud()")
    override def work = {
      super.work()
      println("Human :: work()");
    }
  }

  val human = new Human
  human.work


}
