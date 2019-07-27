package part3

import part3.WhatsAFunction.MyFunction

object AnonymousFunctions extends App {


  //1.

  val doubler1 = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  //2.

  val doubler2 = new Function1[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }


  //3.
  // anonymous function (LAMBDA)
  val doubler: Int => Int = (x: Int) => x * 2

  // multiple params in a lambda
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b


  // no params
  val justDoSomething: () => Int = () => 3


  // careful
  println(justDoSomething) // function itself
  println(justDoSomething()) // call


  // curly braces with lambdas
  val stringToInt = { (str: String) =>
    str.toInt
  }


  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b


  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(3)(4))

}
