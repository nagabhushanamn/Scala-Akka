package part3

import scala._;

object PlayGround_2 extends App {

  val list = List(1, 2, 3)
  //  val newList=list.map(n=>n*2)
  //  val newList = list.map(_ * 2)
  //  val sum = list.reduce((n, acc) => n + acc)
  val newList = list.map {
    x: Int => x * 2
  }


}
