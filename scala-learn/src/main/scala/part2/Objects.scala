package part2

object Objects extends App {


  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")

  object Person { // type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2

    def canFly: Boolean = false
  }

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON INSTANCE


  val person1 = Person
  val person2 = Person
  println(person1 == person2)

  class Person(val name: String) {
    // instance-level functionality
  }


  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john)

  //-------------------------------------------------------


  class Food private(menu: String, price: Double) {
    def this(menu: String) = this(menu, 0.0)
  }

  object Food {
    def apply(menu: String): Food = new Food(menu)

    def apply(menu: String, price: Double): Food = new Food(menu, price)
  }

  // imp-note : companions must be defined in same scala file

  var food1 = Food("VEG")
  var food2 = Food("NON-VEG", 100.00)

}
