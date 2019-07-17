package part3

import part3.why_FP.list

object why_FP extends App {

  val list = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)


  //---------------------------------------------
  // way-1 : Imperative style ( what + how ) code
  //---------------------------------------------

  //  Ex. even
  var evenNums = List[Int]()
  for (ele <- list) {
    if (ele % 2 == 0)
      evenNums = evenNums :+ ele
  }
  println(evenNums)


  //---------------------------------------------
  // way-2 : declarative style ( what  ) code   + with functions ==> FP
  //---------------------------------------------


  // #1  Inner class

  //  Ex. odd
  //  class OddPredicate extends Predicate[Int] {
  //    override def test(e: Int): Boolean = e % 2 != 0
  //  }
  //
  //  val oddPredicate = new OddPredicate()
  //  var oddNums = Lib.filter(list, oddPredicate)
  //  println(oddNums)


  // #2  Anonymous Inner class

  //  val oddPredicate = new Predicate[Int] {
  //    override def test(e: Int): Boolean = e % 2 != 0
  //  }
  //  var oddNums = Lib.filter(list, oddPredicate)
  //  println(oddNums)

  //
  //  // #3 Function`
  //  val oddPredicate: Function1[Int, Boolean] = (e: Int) => e % 2 != 0 // ( Lambda )
  //
  //  var oddNums = Lib.filter(list, oddPredicate)
  //  println(oddNums)


  //  // #4 Function`
  //var oddNums = Lib.filter(list, (e: Int) => e % 2 != 0)
  //  var oddNums = Lib.filter(list, e => e % 2 != 0)
  //  println(oddNums)

  //  // #5 Function`

  println(list.filter(e => e % 2 == 0))

  //-----------------------------------------------

  // Lib
  //
  //
  //  object Lib {
  //    def filter(list: List[Int], predicate: (Int) => Boolean): List[Int] = {
  //      var evenNums = List[Int]()
  //      for (ele <- list) {
  //        if (predicate(ele))
  //          evenNums = evenNums :+ ele
  //      }
  //      evenNums
  //    }
  //  }

}
