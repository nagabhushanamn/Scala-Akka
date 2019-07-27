package part2_primer

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}

object Demo extends App {


  implicit val system = ActorSystem("system")
  implicit val materializer = ActorMaterializer()

  // Source
  val source = Source(List(1, 2, 3))
  // Flow ( filter , traform , limit )
  val mapFlow = Flow[Int].map(_ * 2)
  // Sink
  val sink = Sink.foreach(println)
  // Graph
  val graph = source.via(mapFlow).to(sink)
  graph.run()

}
