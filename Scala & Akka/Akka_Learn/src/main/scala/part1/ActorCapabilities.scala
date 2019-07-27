package part1

import akka.actor.{Actor, ActorRef, ActorSystem, Props}


object ActorCapabilities extends App {


  val system = ActorSystem("system-1")


  class EmployeeActor extends Actor {

    override def receive: Receive = {

      case str: String => println(s"[Employee] : received string $str")
      case number: Int => println(s"[Employee] : received number $number")
      case WhoAreYou => self ! Message("I am an actor and I am proud of it")
      case ReplyTo(ref) => ref ! Message("msg1..")
      case Message(message) => println(s"${self} received $message from ${sender()}"); //sender() ! "hello there"
      case Forward(ref, m) => ref forward  m

    }

  }


  val employee1Actor = system.actorOf(Props[EmployeeActor], "employee1")

  //----------------------------------------------------------------------------
  // 1 - messages can be of any type
  // a) messages must be IMMUTABLE
  // b) messages must be SERIALIZABLE

  // in practice use case classes and case objects
  //----------------------------------------------------------------------------


  //  employee1Actor ! "hello..."
  //  employee1Actor ! 42
  //
  case class Message(message: String)

  //
  //  employee1Actor ! SpecialMessage("you r promoted as an actor")


  //----------------------------------------------------------------------------
  // 2 - actors have information about their context and about themselves
  // context.self === `this` in OOP
  //----------------------------------------------------------------------------

  case object WhoAreYou

  //
    employee1Actor ! WhoAreYou

  //----------------------------------------------------------------------------
  // 3 - actors can REPLY to messages
  //----------------------------------------------------------------------------

  val employee2Actor = system.actorOf(Props[EmployeeActor], "employee2")


  case class ReplyTo(ref: ActorRef)

  //
  //  employee1Actor ! ReplyTo(employee2Actor)

  //----------------------------------------------------------------------------
  // 4 - dead letters
  //----------------------------------------------------------------------------

  //----------------------------------------------------------------------------
  // 5 - forwarding messages
  // D -> A -> B
  // forwarding = sending a message with the ORIGINAL sender
  //----------------------------------------------------------------------------

  case class Forward(to: ActorRef, message: Message)

  employee1Actor ! Forward(employee2Actor, Message("hello, im DL"))



  //----------------------------------------------------------------------------
  // Ex.
  //----------------------------------------------------------------------------

  /**
    * Exercises
    *
    * 1. a Counter actor
    *   - Increment
    *   - Decrement
    *   - Print
    *
    * 2. a Bank account as an actor
    * receives
    *   - Deposit an amount
    *   - Withdraw an amount
    *   - Statement
    * replies with
    *   - Success
    *   - Failure
    *
    * interact with some other kind of actor
    */


  case object Increment

  case object Decrement

  case object Print


  class CounterActor extends Actor {

    var count = 0

    override def receive: Receive = {
      case Increment => count += 1
      case Decrement => count -= 1
      case Print => println(s"[${self}] :  count is $count")
    }
  }

  val counterActor = system.actorOf(Props[CounterActor], "counter-actor")

  //  (1 to 5).foreach(_ => counterActor ! Increment)
  //  (1 to 3).foreach(_ => counterActor ! Decrement)
  //
  //  counterActor ! Print

  //----------------------------------------------------------------------------


  case class Deposit(amount: Int)

  case class Withdraw(amount: Int)

  case object Statement

  case class TransactionSuccess(message: String)

  case class TransactionFailure(reason: String)


  class BankAccountActor extends Actor {
    var funds = 0

    override def receive: Receive = {
      case Deposit(amount) =>
        if (amount < 0) sender() ! TransactionFailure("invalid deposit amount")
        else {
          funds += amount
          sender() ! TransactionSuccess(s"successfully deposited $amount")
        }
      case Withdraw(amount) =>
        if (amount < 0) sender() ! TransactionFailure("invalid withdraw amount")
        else if (amount > funds) sender() ! TransactionFailure("insufficient funds")
        else {
          funds -= amount
          sender() ! TransactionSuccess(s"successfully withdrew $amount")
        }
      case Statement => sender() ! s"Your balance is $funds"
    }
  }


  case class LiveTheLife(accountActor: ActorRef)

  class CustomerActor extends Actor {

    override def receive: Receive = {
      case LiveTheLife(accountActor) =>
        accountActor ! Deposit(10000)
        accountActor ! Withdraw(90000)
        accountActor ! Withdraw(500)
        accountActor ! Statement
      case message => println(message.toString)
    }
  }


  val accountActor = system.actorOf(Props[BankAccountActor], "bank-account")
  val customerActor = system.actorOf(Props[CustomerActor], "billionaire")

//  customerActor ! LiveTheLife(accountActor)


}
