package ex

package v1 {

  abstract class MyList {
    def head: Int
    def tail: MyList
    def isEmpty: Boolean
    def add(x: Int): MyList
    def printElements: String
    override def toString(): String = "[" + printElements + "]"
  }

  object Empty extends MyList {
    def head: Int = throw new NoSuchElementException
    def tail: MyList = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add(x: Int): MyList = new Cons(x, Empty)
    def printElements: String = ""
  }

  class Cons(h: Int, t: MyList) extends MyList {
    def head: Int = h
    def tail: MyList = t
    def isEmpty: Boolean = false
    def add(x: Int): MyList = new Cons(x, this)
    def printElements: String =
      if (t.isEmpty) "" + h
      else h + "," + t.printElements
  }
  object ListTest extends App {
    val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
    //  println(list.head)
    //  println(list.tail.head)
    //  println(list.add(4).head)
    println(list.toString)
  }
}


package v2 {

  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](x: B): MyList[B]
    def printElements: String
    override def toString(): String = "[" + printElements + "]"
  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](x: B): MyList[B] = new Cons(x, Empty)
    def printElements: String = ""
  }

  class Cons[A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](x: B): MyList[B] = new Cons(x, this)
    def printElements: String =
      if (t.isEmpty) "" + h
      else h + "," + t.printElements
  }


  object ListTest extends App {
    val list = new Cons[Int](0, new Cons(2, new Cons(3, Empty)))
    println(list.toString)
  }

}


package v3 {
  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](x: B): MyList[B]
    def ++[B >: A](list: MyList[B]): MyList[B]
    def printElements: String
    override def toString(): String = "[" + printElements + "]"
    def map[B](transformer: MyTransformer[A, B]): MyList[B]
    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
    def filter(predicate: MyPredicate[A]): MyList[A]
  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](x: B): MyList[B] = new Cons(x, Empty)
    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
    def printElements: String = ""
    def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
    def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
    def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
  }

  class Cons[A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](x: B): MyList[B] = new Cons(x, this)
    def ++[B >: A](list: MyList[B]): MyList[B] =
      new Cons(h, t ++ list)
    def printElements: String =
      if (t.isEmpty) "" + h
      else h + "," + t.printElements

    def map[B](transformer: MyTransformer[A, B]): MyList[B] =
      new Cons(transformer.transform(h), t.map(transformer))

    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
      transformer.transform(h) ++ t.flatMap(transformer)

    def filter(predicate: MyPredicate[A]): MyList[A] =
      if (predicate.test(h)) new Cons(h, t.filter(predicate))
      else t.filter(predicate)

  }

  trait MyPredicate[-T] {
    def test(elem: T): Boolean
  }

  trait MyTransformer[-A, B] {
    def transform(elem: A): B
  }


  object ListTest extends App {
    val list = new Cons[Int](1, new Cons(2, new Cons(3, Empty)))
    val anotherList = new Cons[Int](0, new Cons(2, Empty))
    val listOfIntegers = list.map(new MyTransformer[Int, Int] {
      override def transform(elem: Int): Int = elem * elem
    })
    println(listOfIntegers.toString())

    val listOfEvens = list.filter(new MyPredicate[Int] {
      override def test(elem: Int): Boolean = elem % 2 == 0
    })

    println(listOfEvens.toString())
    println(list ++ anotherList)
    println(list.flatMap(new MyTransformer[Int, MyList[Int]] {
      override def transform(elem: Int): MyList[Int] = new Cons[Int](elem, new Cons[Int](elem + 1, Empty))
    }).toString())


  }


}


package v4 {


  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](x: B): MyList[B]
    def ++[B >: A](list: MyList[B]): MyList[B]
    def printElements: String
    override def toString(): String = "[" + printElements + "]"
    def map[B](transformer: A => B): MyList[B]
    def flatMap[B](transformer: A => MyList[B]): MyList[B]
    def filter(predicate: A => Boolean): MyList[A]
  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](x: B): MyList[B] = new Cons(x, Empty)
    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
    def printElements: String = ""
    def map[B](transformer: Nothing => B): MyList[B] = Empty
    def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
    def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  }

  class Cons[A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](x: B): MyList[B] = new Cons(x, this)
    def ++[B >: A](list: MyList[B]): MyList[B] =
      new Cons(h, t ++ list)

    def printElements: String =
      if (t.isEmpty) "" + h
      else h + "," + t.printElements

    def map[B](transformer: A => B): MyList[B] =
      new Cons(transformer(h), t.map(transformer))

    def flatMap[B](transformer: A => MyList[B]): MyList[B] =
      transformer(h) ++ t.flatMap(transformer)

    def filter(predicate: A => Boolean): MyList[A] =
      if (predicate(h)) new Cons(h, t.filter(predicate))
      else t.filter(predicate)

  }


  object ListTest extends App {

    val list = new Cons[Int](1, new Cons(2, new Cons(3, Empty)))
    val anotherList = new Cons[Int](0, new Cons(2, Empty))
    val listOfIntegers = list.map(new Function1[Int, Int] {
      override def apply(elem: Int): Int = elem * elem
    })

    println(listOfIntegers.toString())
    val listOfEvens = list.filter(new Function1[Int, Boolean] {
      override def apply(elem: Int): Boolean = elem % 2 == 0
    })

    println(listOfEvens.toString())
    println(list ++ anotherList)
    println(list.flatMap(new Function1[Int, MyList[Int]] {
      override def apply(elem: Int): MyList[Int] = new Cons[Int](elem, new Cons[Int](elem + 1, Empty))
    }).toString())


  }


}


package v5 {


  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](x: B): MyList[B]
    def ++[B >: A](list: MyList[B]): MyList[B]
    def printElements: String
    override def toString(): String = "[" + printElements + "]"
    def map[B](transformer: A => B): MyList[B]
    def flatMap[B](transformer: A => MyList[B]): MyList[B]
    def filter(predicate: A => Boolean): MyList[A]
  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](x: B): MyList[B] = new Cons(x, Empty)
    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
    def printElements: String = ""
    def map[B](transformer: Nothing => B): MyList[B] = Empty
    def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
    def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
  }

  class Cons[A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](x: B): MyList[B] = new Cons(x, this)
    def ++[B >: A](list: MyList[B]): MyList[B] =
      new Cons(h, t ++ list)

    def printElements: String =
      if (t.isEmpty) "" + h
      else h + "," + t.printElements


    def map[B](transformer: A => B): MyList[B] =
      new Cons(transformer(h), t.map(transformer))

    def flatMap[B](transformer: A => MyList[B]): MyList[B] =
      transformer(h) ++ t.flatMap(transformer)

    def filter(predicate: A => Boolean): MyList[A] =
      if (predicate(h)) new Cons(h, t.filter(predicate))
      else t.filter(predicate)


  }


  object ListTest extends App {

    val list = new Cons[Int](1, new Cons(2, new Cons(3, Empty)))
    val anotherList = new Cons[Int](0, new Cons(2, Empty))

    val listOfIntegers = list.map(elem => elem * elem)

    println(listOfIntegers.toString())

    val listOfEvens = list.filter(elem => elem % 2 == 0);

    println(listOfEvens.toString())

    println(list ++ anotherList)

    println(list.flatMap((elem: Int) => new Cons[Int](elem, new Cons[Int](elem + 1, Empty))).toString())


  }


}


package v6 {


  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](x: B): MyList[B]
    def ++[B >: A](list: MyList[B]): MyList[B]
    def printElements: String
    override def toString(): String = "[" + printElements + "]"
    def map[B](transformer: A => B): MyList[B]
    def flatMap[B](transformer: A => MyList[B]): MyList[B]
    def filter(predicate: A => Boolean): MyList[A]
    def forEach(f: A => Unit): Unit
    def sort(compare: (A, A) => Int): MyList[A]

  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing](x: B): MyList[B] = new Cons(x, Empty)
    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
    def printElements: String = ""
    def map[B](transformer: Nothing => B): MyList[B] = Empty
    def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
    def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty
    def forEach(f: Nothing => Unit): Unit = ()
    override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty


  }

  class Cons[A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A](x: B): MyList[B] = new Cons(x, this)
    def ++[B >: A](list: MyList[B]): MyList[B] =
      new Cons(h, t ++ list)

    def printElements: String =
      if (t.isEmpty) "" + h
      else h + "," + t.printElements


    def map[B](transformer: A => B): MyList[B] =
      new Cons(transformer(h), t.map(transformer))

    def flatMap[B](transformer: A => MyList[B]): MyList[B] =
      transformer(h) ++ t.flatMap(transformer)

    def filter(predicate: A => Boolean): MyList[A] =
      if (predicate(h)) new Cons(h, t.filter(predicate))
      else t.filter(predicate)

    override def forEach(f: A => Unit): Unit = {
      f(h)
      t.forEach(f)
    }

    def sort(compare: (A, A) => Int): MyList[A] = {
      def insert(x: A, sortedList: MyList[A]): MyList[A] =
        if (sortedList.isEmpty) new Cons(x, Empty)
        else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
        else new Cons(sortedList.head, insert(x, sortedList.tail))

      val sortedTail = t.sort(compare)
      insert(h, sortedTail)
    }


  }


  object ListTest extends App {

    val list = new Cons[Int](1, new Cons(2, new Cons(3, Empty)))
    val anotherList = new Cons[Int](0, new Cons(2, Empty))

    val listOfIntegers = list.map(elem => elem * elem)

    println(listOfIntegers.toString())

    val listOfEvens = list.filter(elem => elem % 2 == 0);

    println(listOfEvens.toString())

    println(list ++ anotherList)

    println(list.flatMap((elem: Int) => new Cons[Int](elem, new Cons[Int](elem + 1, Empty))).toString())


    list.forEach(println)

    println(list.sort((x,y)=>y-x))

  }


}
