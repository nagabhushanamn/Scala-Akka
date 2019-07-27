package part2

object OObasics extends App {

  // 1. immutable fields
  class PersonEx1 {

    val name = "Nag"
    val age = 36

    def sayName() = {
      println("im " + this.name)
    }

    def sayAge(): Unit = {
      println("im " + this.age + " yrs old..")
    }

    println("Person constructed..")

  }

  //  val person = new PersonEx1
  //  person.name="Nag N";  // immutable error
  //  person.age=37;  // immutable error
  //  println(person.name)
  //  println(person.age)
  //  person.sayName();
  //  person.sayAge();


  // 2. mutable fields
  class PersonEx2 {

    var name = "Nag"
    var age = 36

    def sayName() = {
      println("im " + this.name)
    }

    def sayAge(): Unit = {
      println("im " + this.age + " yrs old..")
    }

  }

  //  val person = new PersonEx2
  //  person.name = "Nag N";
  //  person.age = 37;
  //  println(person.name)
  //  println(person.age)
  //  person.sayName();
  //  person.sayAge();


  // 3. private & mutable fields
  class PersonEx3 {

    private var name = "Nag"
    private var age = 36

    def sayName() = {
      println("im " + this.name)
    }

    def sayAge(): Unit = {
      println("im " + this.age + " yrs old..")
    }

    def changeName(name: String) = {
      this.name = name;
    }

  }

  //  val person = new PersonEx3
  //  person.changeName("Nag N")
  //  person.sayName();


  // 4. private immutable fields

  class PersonEx4 {

    private val name = "Nag"
    private val age = 36

    def sayName() = {
      println("im " + this.name)
    }

    def sayAge(): Unit = {
      println("im " + this.age + " yrs old..")
    }

    def changeName(name: String) = {
      // this.name = name; // immutable error
    }


  }


  // 5. primary constructor + encapsulated fields

  class PersonEx5(name: String, age: Int) {


    def sayName() = {
      println("im " + this.name)
    }

    def sayAge(): Unit = {
      println("im " + this.age + " yrs old..")
    }

    def changeName(name: String) = {
      // this.name = name; // immutable error
    }

  }

  //  val person = new PersonEx5("Nag", 35)
  //  println(person.name)


  // 6.  primary constructor + immutable fields

  class PersonEx6(val name: String, val age: Int) {


    def sayName() = {
      println("im " + this.name)
    }

    def sayAge(): Unit = {
      println("im " + this.age + " yrs old..")
    }

    def changeName(name: String) = {
      // this.name = name; // immutable error
    }

  }

  //  val person = new PersonEx6("Nag", 35)
  //  println(person.name)


  // 7. primary constructor + mutable fields

  class PersonEx7(var name: String, var age: Int) {


    def sayName() = {
      println("im " + this.name)
    }

    def sayAge(): Unit = {
      println("im " + this.age + " yrs old..")
    }

    def changeName(name: String) = {
      this.name = name;
    }

  }

  //  val person = new PersonEx7("Nag", 35)
  //  person.name = "Nag N";
  //  println(person.name)


  // 8.

  class PersonEx8(private var name: String, var age: Int) {


    def sayName() = {
      println("im " + this.name)
    }

    def sayAge(): Unit = {
      println("im " + this.age + " yrs old..")
    }

    def changeName(name: String) = {
      this.name = name;
    }

  }

  //  val person = new PersonEx8("Nag", 35)

  //  person.name = "Nag N"; // immutable error
  //  println(person.name)  // visibility error


  // 9. Auxilary constructor(s)

  class PersonEx9(private var name: String, var age: Int) {

    def this(name: String) {
      this(name, 0)
    }

    def this(age: Int) {
      this("Nil", age)
    }

    def this() {
      this("Nil")
    }

    def sayName() = {
      println("im " + this.name)
    }

    def sayAge(): Unit = {
      println("im " + this.age + " yrs old..")
    }

    def changeName(name: String) = {
      this.name = name;
    }

  }

  //  val person=new PersonEx9




  // 10. Ex. Counter


  class Counter(val count: Int = 0) {
    def inc = {
      println("incrementing...")
      new Counter(count + 1) // immutability..
    }

    def inc(n: Int): Counter = {
      if (n <= 0) this
      else inc.inc(n - 1)
    }

    def dec = {
      println("decrementing...")
      new Counter(count - 1) // immutability..
    }

    def dec(n: Int): Counter = {
      if (n <= 0) this
      else dec.dec(n - 1)
    }

    def print = {
      println(s"count : $count")
    }

  }


  val counter = new Counter()
  counter.inc.inc.inc.dec.inc.print


}
