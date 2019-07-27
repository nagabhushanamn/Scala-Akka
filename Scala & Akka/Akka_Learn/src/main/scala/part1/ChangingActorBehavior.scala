package part1

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ChangingActorBehavior extends App {

  //  ------------------------------------------------------------------------------
  // Ex.1
  //  ------------------------------------------------------------------------------

  object MomActor {

    val CHOCOLATE = "chocolate"
    val VEGETABLE = "vegetable"

    case class Food(foodType: String)

    case class Ask(ques: String)

    case class MomStart(kid: ActorRef)

  }

  class MomActor extends Actor {

    import MomActor._
    import KidActor._

    override def receive: Receive = {
      case MomStart(kidRef) => {
        kidRef ! Food(VEGETABLE)
        kidRef ! Food(VEGETABLE)
        kidRef ! Food(CHOCOLATE)
//        kidRef ! Food(CHOCOLATE)
        kidRef ! Ask("common kid , let's play")
      }
      case Accept => println("Yay, my kid is happy!")
      case Reject => println("My kid is sad, but as he's healthy!")
    }
  }


  object KidActor {

    val HAPPY = "happy"
    val SAD = "sad"

    case object Accept

    case object Reject

  }

  class KidActor extends Actor {

    import MomActor._
    import KidActor._

    var state = HAPPY

    override def receive: Receive = {
      case Food(CHOCOLATE) => state = HAPPY
      case Food(VEGETABLE) => state = SAD
      case Ask(_) => {
        if (state == HAPPY) sender() ! Accept
        else sender() ! Reject
      }
    }
  }


  // v2
  class StatelessKidActor extends Actor {

    import MomActor._
    import KidActor._

    override def receive: Receive = happyReceive


    def happyReceive: Receive = {
      case Food(VEGETABLE) => context.become(sadReceive, false)
      case Food(CHOCOLATE) =>
      case Ask(_) => sender() ! Accept
    }

    def sadReceive: Receive = {
      case Food(VEGETABLE) => context.become(sadReceive, false)
      case Food(CHOCOLATE) => context.unbecome();
      case Ask(_) => sender() ! Reject
    }

  }


  val system = ActorSystem("system1")

  val momActor = system.actorOf(Props[MomActor], "mom")
  //  val kidActor = system.actorOf(Props[KidActor], "kid")

  val kidActor = system.actorOf(Props[StatelessKidActor], "kid")

  import MomActor._

  momActor ! MomStart(kidActor)

  /*
    mom receives MomStart
      kid receives Food(veg) -> kid will change the handler to sadReceive
      kid receives Ask(play?) -> kid replies with the sadReceive handler =>
    mom receives Reject
  */


  /*
  context.become
    Food(veg) -> stack.push(sadReceive)
    Food(chocolate) -> stack.push(happyReceive)
    Stack:
    1. happyReceive
    2. sadReceive
    3. happyReceive
   */


  /*

    E.g

    Food(veg)
    Food(veg)
    Food(choco)
    Food(choco)
    Stack:
    1. happyReceive
    1. happyReceive
    1. sadReceive
    1. sadReceive
    1. happyReceive


   */


  //  ------------------------------------------------------------------------------
  /**
    * Exercises
    *
    * 1 - recreate the Counter Actor with context.become and NO MUTABLE STATE
    */
  //  ------------------------------------------------------------------------------


  object CounterActor {

    case object Increment

    case object Decrement

    case object Print

  }

  class CounterActor extends Actor {

    import CounterActor._

    override def receive: Receive = countReceive(0)

    def countReceive(currentCount: Int): Receive = {
      case Increment =>
        println(s"[counterActor receive($currentCount)] incrementing")
        context.become(countReceive(currentCount + 1))
      case Decrement =>
        println(s"[counterActor receive($currentCount)] decrementing")
        context.become(countReceive(currentCount - 1))
      case Print => println(s"[counterActor receive($currentCount)] my current count is $currentCount")
    }
  }

  import CounterActor._

  val counter = system.actorOf(Props[CounterActor], "counterActor")

  (1 to 5).foreach(_ => counter ! Increment)
  (1 to 3).foreach(_ => counter ! Decrement)

  counter ! Print


}
