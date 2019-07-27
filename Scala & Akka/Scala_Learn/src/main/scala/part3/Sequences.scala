package part3

object Sequences extends App {

  //1.

  // Seq
  val aSequence = Seq(1, 3, 2, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7, 5, 6))
  println(aSequence.sorted)


  //2.

  // Ranges
  val aRange: Seq[Int] = 1 until 10
  aRange foreach println


  //3.

  (1 to 10).foreach(x => println("Hello"))


  //4.

  // lists
  val aList = List(1, 2, 3)
  val prepended = 42 +: aList :+ 89
  println(prepended)


  val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))


  // 5.
  // arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[String](3)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  // vectors
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)


}
