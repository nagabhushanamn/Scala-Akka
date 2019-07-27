package part1

import akka.actor.{Actor, ActorSystem, Props}

object ActorsIntro extends App {

  //------------------------------------------------------------------
  // Ex.1 Intro
  //------------------------------------------------------------------
  // step-1: create actor system
  val system = ActorSystem("system-1")

  //  println(system)

  // step-2: define actor class
  class SomeActor extends Actor {
    // internal data
    // behavior
    override def receive: PartialFunction[Any, Unit] = {
      case message: String =>
        println(s"[SomeActor] : received string message : $message")
    }
  }

  //step-3: create actor
  val aActor = system.actorOf(Props[SomeActor], "a")
  val bActor = system.actorOf(Props[SomeActor], "b")

  // step-4: communicate
  aActor ! "Hello.." // Asynchronous
  bActor ! "Hi"


  //------------------------------------------------------------------
  // Ex. define 'Word Count' actor ?
  //------------------------------------------------------------------

  case object PrintCount

  class WordCountActor extends Actor {
    // internal data
    var totalWords = 0

    // behavior
    def receive: Receive = {
      case message: String =>
        totalWords += message.split(" ").length
      case PrintCount => println(s"[WordCountActor] current word count : $totalWords")
      case msg => println(s"[WordCountActor]  I cannot understand ${msg.toString}")
    }
  }


  val aWordCountActor = system.actorOf(Props[WordCountActor])
  aWordCountActor ! "i love akka"
  aWordCountActor ! "i love my life"
  aWordCountActor ! PrintCount


}
