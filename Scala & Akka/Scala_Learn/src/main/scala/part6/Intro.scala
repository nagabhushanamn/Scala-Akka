package part6

import java.util.concurrent.Executors

object Intro extends App {


  // 1.
  // JVM threads
  val runnable = new Runnable {
    override def run(): Unit = println("Running in parallel")
  }
  val aThread = new Thread(runnable)

  aThread.start() // gives the  signal to the JVM to start a JVM thread
  // create a JVM thread => OS thread
  runnable.run() // doesn't do anything in parallel!
  aThread.join() // blocks until aThread finishes running

  //2.

  val threadHello = new Thread(() => (1 to 5).foreach(_ => println("hello")))
  val threadGoodbye = new Thread(() => (1 to 5).foreach(_ => println("goodbye")))
  //  threadHello.start()
  //  threadGoodbye.start()
  // different runs produce different results!


  //3.
  // executors
  val pool = Executors.newFixedThreadPool(10)
  //  pool.execute(() => println("something in the thread pool"))

  //4.

  def runInParallel = {
    var x = 0

    val thread1 = new Thread(() => {
      x = 1
    })

    val thread2 = new Thread(() => {
      x = 2
    })

    thread1.start()
    thread2.start()
    println(x)
  }

  // for (_ <- 1 to 10000) runInParallel
  // race condition


  //5.


  class BankAccount(@volatile var amount: Int) {
    override def toString: String = "" + amount
  }

  def buy(account: BankAccount, thing: String, price: Int) = {
    account.synchronized({
      account.amount -= price // account.amount = account.amount - price
    })
    //    println("I've bought " + thing)
    //    println("my account is now " + account)
  }

  for (_ <- 1 to 10000) {
    val account = new BankAccount(50000)
    val thread1 = new Thread(() => buy(account, "shoes", 3000))
    val thread2 = new Thread(() => buy(account, "iPhone12", 4000))

    thread1.start()
    thread2.start()
    Thread.sleep(10)
    if (account.amount != 43000) println("AHA: " + account.amount)
    //    println()
  }
  /*
   thread1 (shoes): 50000
     - account = 50000 - 3000 = 47000
   thread2 (iphone): 50000
     - account = 50000 - 4000 = 46000 overwrites the memory of account.amount
  */


  // option #1: use synchronized()
  def buySafe(account: BankAccount, thing: String, price: Int) =
    account.synchronized {
      // no two threads can evaluate this at the same time
      account.amount -= price
      println("I've bought " + thing)
      println("my account is now " + account)
    }

  // option #2: use @volatile


}
