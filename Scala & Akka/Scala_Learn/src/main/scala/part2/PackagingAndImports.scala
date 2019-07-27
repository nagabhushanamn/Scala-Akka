package part2


import java.util.Date
import java.sql.{Date => SqlDate}

package playground {

  class Princess

}

object PackagingAndImports extends App {


  // packages are in hierarchy
  // matching folder structure.

  val princess = new part2.playground.Princess

  import part2.playground._

  val princess2 = new Princess

  sayHello()

  val utilDate = new Date()
  val sqlDate = new SqlDate(utilDate.getTime)


  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???


}
