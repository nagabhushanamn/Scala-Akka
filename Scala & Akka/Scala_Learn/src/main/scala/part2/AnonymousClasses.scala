package part2


object AnonymousClasses extends App {


  abstract class Comparator[T] {
    def compare(o1: T, o2: T): Int;
  }


  val integerComparator = new Comparator[Int] {
    override def compare(o1: Int, o2: Int): Int = o1 - o2
  }



}
