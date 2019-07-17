package part2

// 1.
object Inheritance_1 extends App {

  class Employee(val id: Int, name: String, salary: Double) {
    val compName = "XXX";
  }

  class TechEmployee(id: Int, name: String, salary: Double, val techSkill: String) extends Employee(id, name, salary) {
    override val compName = "siemens"

    def print = {
      println(compName)
    }
  }

  val techEmployee = new TechEmployee(111, "Nag", 100.00, "java")
  techEmployee print

}


object Inheritance_2 extends App {


  abstract class LivingThing {

    def eat = println("LT :: eat()")

    def sleep = println("LT :: sleep()")

    def work

  }

  class Human extends LivingThing {
    def study = println("Hu :: study()")

    override def work = println("Hu :: work")
  }

  class Animal extends LivingThing {
    override def work: Unit = println("work....")
  }

  class Robot extends LivingThing {
    override def eat: Unit = println("Ro :: eat")

    override def work: Unit = println("Ro :: work")
  }

  // principle : open for extention & closed for modification principle
  class God {

    //    def manageHuman(human: Human) = {
    //      human.eat
    //      human.sleep
    //      human.study
    //      human.work
    //    }
    //
    //    def manageAnimal(animal: Animal) = {
    //      animal.eat
    //      animal.sleep
    //      animal.work
    //    }

    def manageLT(lt: LivingThing) = {
      lt.sleep
      lt.eat
      lt.work
    }


  }


  val human = new Human
  val animal = new Animal
  val robot = new Robot


  val god = new God
  god.manageLT(human)
  println("")
  god.manageLT(animal)
  println("")
  god.manageLT(robot)


}
