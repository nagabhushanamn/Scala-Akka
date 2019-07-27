package part4

import akka.actor.{Actor, ActorRef, FSM}
import akka.event.LoggingReceive

object FSM_Demo extends App {


  object BankAccount {

    case class Deposit(amount: BigInt) {
      require(amount > 0)
    }

    case class Withdraw(amount: BigInt) {
      require(amount > 0)
    }

    case object Done

    case object Failed

  }

  class BankAccount extends Actor {

    import BankAccount._

    var balance = BigInt(0)

    def receive = LoggingReceive {
      case Deposit(amount) =>
        balance += amount
        sender ! Done

      case Withdraw(amount) if amount <= balance =>
        balance -= amount
        sender ! Done

      case _ => sender ! Failed
    }
  }


  object WireTransfer {

    case class Transfer(from: ActorRef, to: ActorRef, amount: BigInt)

    case object Done

    case object Failed

  }

  class WireTransfer extends Actor {

    import WireTransfer._

    def receive: Receive = LoggingReceive {
      case Transfer(from, to, amount) =>
        from ! BankAccount.Withdraw(amount)
        context.become(awaitFrom(to, amount, sender))
    }

    def awaitFrom(to: ActorRef, amount: BigInt, customer: ActorRef): Receive = LoggingReceive {
      case BankAccount.Done =>
        to ! BankAccount.Deposit(amount)
        context.become(awaitTo(customer))
      case BankAccount.Failed =>
        customer ! Failed
        context.stop(self)
    }

    def awaitTo(customer: ActorRef): Receive = LoggingReceive {
      case BankAccount.Done =>
        customer ! Done
        context.stop(self)
    }
  }


  /**
    *
    * FSM
    *
    * State\(S\)×Event\(E\)⟹Actions\(A\),State\(S′\)
    *
    * which informally means that if we are in state State(S)
    * and an Event(E) occurs then actions Actions(A) should be performed
    * and the system should transition to State(S′).
    */


}

package FSM {

  sealed trait State

  object Initial extends State

  object AwaitFrom extends State

  object AwaitTo extends State

  object Done extends State


  sealed trait Data

  case object UninitializedWireTransferData extends Data

  case class InitialisedWireTransferData(from: ActorRef, to: ActorRef, amount: BigInt, client: ActorRef) extends Data



  class WireTransfer extends FSM[State, Data] {

    startWith(Initial, UninitializedWireTransferData)

    import part4.FSM_Demo.BankAccount
    import part4.FSM_Demo.WireTransfer.Transfer
    import part4.FSM_Demo.WireTransfer

    when(Initial) {
      case Event(Transfer(from, to, amount), UninitializedWireTransferData) =>
        from ! BankAccount.Withdraw(amount)
        goto(AwaitFrom) using InitialisedWireTransferData(from, to, amount, sender())
    }

    when(AwaitFrom) {
      case Event(BankAccount.Done, InitialisedWireTransferData(_, to, amount, _)) =>
        to ! BankAccount.Deposit(amount)
        goto(AwaitTo)
      case Event(BankAccount.Failed, InitialisedWireTransferData(_, _, _, client)) =>
        client ! WireTransfer.Failed
        goto(Done)
        stop()
    }

    when(AwaitTo) {
      case Event(BankAccount.Done, InitialisedWireTransferData(_, _, _, client)) =>
        client ! WireTransfer.Done
        goto(Done)
        stop()
      case Event(BankAccount.Failed, InitialisedWireTransferData(_, _, _, client)) =>
        client ! WireTransfer.Failed
        goto(Done)
        stop()
    }

    initialize()

  }

}



