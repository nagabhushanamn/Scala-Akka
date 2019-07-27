package part1

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.event.Logging

object ActorLoggingEx extends App {

  //--------------------------------------------------------------
  // #way-1  ==> explicit logging
  //--------------------------------------------------------------

//  class SimpleActorWithExplicitLogger extends Actor {
//
//    val logger = Logging(context.system, this)
//
//    override def receive: Receive = {
//      case message => logger.info(message.toString) // LOG it
//    }
//  }
//
//
  val system = ActorSystem("LoggingDemo")
//  val actor = system.actorOf(Props[SimpleActorWithExplicitLogger])
//
//  actor ! "Logging a simple message"
//

  //

  //--------------------------------------------------------------
  // #2 - ActorLogging
  //--------------------------------------------------------------
  class ActorWithLoggingEx extends Actor with ActorLogging {
    override def receive: Receive = {
      case (a, b) => log.info("Two things: {} and {}", a, b) // Two things: 2 and 3
      case message => log.info(message.toString)
    }
  }

  val simplerActor = system.actorOf(Props[ActorWithLoggingEx])
  simplerActor ! "Logging a simple message by extending a trait"
  simplerActor ! (42, 65)


}





