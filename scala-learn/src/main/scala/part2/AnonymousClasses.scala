package part2

object AnonymousClasses extends App {


  abstract class Comparator[T] {
    def compare(o1: T, o2: T): Int;
  }


  // a. Named implementation classes
  class IntComparator extends Comparator[Int] {
    override def compare(o1: Int, o2: Int): Int = {
      0
    }
  }

  val intComparator = new IntComparator


  // b. Anonymous

  val stringComparator = new Comparator[String] {
    override def compare(o1: String, o2: String): Int = {
      0
    }
  }

}
