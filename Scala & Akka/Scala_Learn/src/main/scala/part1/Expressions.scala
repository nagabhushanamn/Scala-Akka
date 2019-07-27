package part1

object Expressions extends App {

  /**
    * Instructions (DO) vs Expressions (VALUE)
    */

  // Instructions   => side effects
  var aConditionedValue: Int = 0
  if (true) {
    aConditionedValue = 1
  }
  else {
    aConditionedValue = 2
  }


  //  Expressions (VALUE) => no side effects
  val anotherConditionedValue = if (true) 1 else 2

  // imp note : EVERYTHING in Scala is an Expression!

  var i = 0
  val aWhile = while (i < 10) {
    println(i)
    i += 1
  }
  println(aWhile) // unit === void


  val aCodeBlock = {
    val y = 2
    val z = y + 1
    if (z > 2) "hello" else "goodbye"
  }

  var aVariable = 0;
  val aWeiredValue = (aVariable = 3)
  println(aWeiredValue)


  // statements ==> imperative style  |  expressions ==> functional programming
  val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  var sum = 0;
  for (i <- 1 until 10) {
    if (arr(i) % 2 == 0) {
      sum += arr(i)
    }
  }
  println(sum)

  val sum1 = arr.filter(n => n % 2 == 0).sum;
  println(sum1)


  // Ex.
  def totalImperative(list: List[Int]) = {
    var sum = 0;
    for (i <- list) {
      sum += i
    }
    sum
  }

  println(totalImperative(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))

  def totalFunctional(list: List[Int]) = {
    list.foldLeft(0) { (carryOver, e) =>
      carryOver + e
    }
  }
  // FP     ==> assignment less programming
  //        ==> function don't have side effects



}
