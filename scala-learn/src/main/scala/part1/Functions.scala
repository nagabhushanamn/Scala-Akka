package part1

object Functions extends App {


  // 1.

  def aFunction(x: Int, y: Int): Int = {
    x + y
  }

  println(aFunction(1, 2))

  //2.

  def aParameterLessFunction() = 42

  println(aParameterLessFunction())
  println(aParameterLessFunction)

  // 3.

  // Imp-Note :   WHEN YOU NEED LOOPS, USE RECURSION.

  def aRepeatedFunction(str: String, n: Int): String = {
    if (n == 1) str;
    else str + aRepeatedFunction(str, n - 1);
  }

  println(aRepeatedFunction("hello", 3))


  // 4.

  def factorial(n: Int): Int = {
    if (n <= 0) 1
    else n * factorial(n - 1)
  }

  println(factorial(5))

  //5.

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  // 6.
  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
  }


  //6. CBN & CBV


  def calledByValue(x: Long): Unit = {
    println("by value " + x)
    println("by value " + x)
  }

  calledByValue(System.nanoTime())


  def calledByName(x: => Long): Unit = {
    println("by name " + x)
    println("by name " + x)
  }

  calledByName(System.nanoTime())


  // 7. Default/Named Args


  def fact(n: Int, acc: Int = 1): Int =
    if (n <= 1) acc;
    else fact(n - 1, n * acc)


  fact(5)


  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture")

  savePicture(width = 100)


}
