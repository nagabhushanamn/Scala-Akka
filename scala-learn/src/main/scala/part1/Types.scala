package part1

object Types extends App {

  /*

   Types
   ------------
    Any
      - AnyValue
      - AnyRef ( java.lang.Object )
   */


  // values ==> immutable

  val x: Int = 36;
  val y = 36; // // compiler can infer types


  // variables

  var z = 10;
  z = 20 // side effects , => discouraged in concurrent & parallel applications


}
