package part2


object Generics extends App {


  //1.

  trait Subject

  class Java extends Subject

  class Scala extends Subject

  //1. generic types

  class Trainer[T <: Subject](var sub: T)

  val javaTrainer = new Trainer[Java](new Java)
  //  javaTrainer.sub = new Scala  // type safety

  //  val stringTrainer = new Trainer[String]("Java")
  //  val intTrainer = new Trainer[Int](123)
  //

  //2. generic methods

  def print[E, T](ele: E, pre: T): Unit = {
    println(ele)
  }

  print("hello", ":)")
  print(123, ":(")


  // variance problem

  class Animal

  class Cat extends Animal

  class Dog extends Animal


  // 1. INVARIANCE
  class InvariantList[A]

  val list: InvariantList[Number] = new InvariantList[Number];

  //  val intList:InvariantList[Number]=new InvariantList[Int];


  // 2. List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]


  // 3. CONTRAVARIANCE
  class Trainer1[-A]

  val trainer: Trainer1[Cat] = new Trainer1[Animal]

}
