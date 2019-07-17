package part1

object Expressions extends App {


  /**
    * Instructions/statements (DO) vs Expressions (VALUE)
    */


  // Instructions   => side effects
  var aConditionedVariable = 0
  if (true) {
    aConditionedVariable = 1
  } else {
    aConditionedVariable = 2
  }

  //  Expressions (VALUE) => no side effects
  val anotherConditionedValue = if (true) 1 else 2

  // imp note : EVERYTHING in Scala is an Expression!


  // loop
  //

  var i = 0;
  val aWhile = while (i < 5) {
    println(i)
    i += 1
  }
  println(aWhile) // () == Unit == void


  // code blocks

  val aComplexValue = {
    val x = 12;
    val y = 13;
    x + y
  }
  println(aComplexValue)


}
