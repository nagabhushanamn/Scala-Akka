package part3

object PlayGround_1 extends App {

  // OOP

  // #1. Function as value/object  i.e first class citizens/elements
  val booleanFunc: Function1[Int, Boolean] = new Function[Int, Boolean] {
    override def apply(v1: Int): Boolean = v1 > 100
  }

  // FP

  val isMin = (v1: Int) => v1 <= 1000;
  val isMax = (v1: Int) => v1 >= 100;

  //  #2. parameter of function can be other function
  //  #3. return value of function can another function
  def and(f1: Int => Boolean, f2: Int => Boolean): Int => Boolean =
    (v: Int) => f1(v) && f2(v)

  val newF = and(isMin, isMax)
  //  println(newF(150))


  //--------------------------------------------------------------------

  //
  //  val f1 = () => {
  //    println("before :: LOG")
  //    println("im f1()")
  //    println("after :: LOG")
  //  }
  //
  //  f1();
  //
  //  val f2 = () => {
  //    println("before :: LOG")
  //    println("im f2()")
  //    println("after :: LOG")
  //  }
  //  f2()

  /*

    design issues

    => code tangling/tight-coupling
    => code scattering / duplication

   */


  val f1 = () => {
    println("im f1()")
  }


  val f2 = () => {
    println("im f2()")
  }

  // Higher-Order-Function ( HOF )
  val logWrapper = (f: () => Unit) => {
    () => {
      println("before :: LOG")
      f();
      println("after :: LOG")
    }
  }


  val f1WithLog = logWrapper(f1)
  f1WithLog();

  val f2WithLog = logWrapper(f2);
  f2WithLog();


  //-------------------------------------------------
  // Function Currying ==> HOF
  //-------------------------------------------------

  def supperAdder1 = (x: Int, y: Int) => x + y

  println(supperAdder1(12, 13))

  def superAddrer2 = (x: Int) => (y: Int) => x + y

  val newFF = superAddrer2(12);
  println(newFF(13))

  def superAdder3(x: Int)(y: Int) = x + y // function currying

  val newFFF: Int => Int = superAdder3(12);
  println(newFFF(13))


  def htmlElementWrapper(startTag: String, endTag: String)(data: String) =
    startTag + data + endTag

  val divElementWrapper: String => String = htmlElementWrapper("<div>", "</div>")

  val divElement = divElementWrapper("simens")

  println(divElement)


  //--------------------------------------------------------------

  // closure


  val teach = (sub: String) => {
    println("teaching " + sub)
    val notes = s"$sub -notes"
    val learn = () => {
      println(s"learning with $notes")
    }
    println("teachings ends..")
    learn
  }

  val learnFunc = teach("scala")
  learnFunc();


}
