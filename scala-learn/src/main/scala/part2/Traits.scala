package part2

object Traits extends App {


  // why we need interface / trait ?


  /*

      Txr-system

        - use-case ==> txr(amount,from,to)

          service    : TxrService  ==> processing logic
          repository : AccountRepository ==> persisting logic


   */

  class Account(val number: String, balance: Double)

  //-------------------------------------------------------------------------------------

  trait AccountRepository {
    def loadAccount(accNumber: String): Account

    def updateAccount(account: Account): Unit
  }

  class JdbcAccountRepository extends AccountRepository {
    def loadAccount(accNumber: String) = new Account(accNumber, 1000.00)

    def updateAccount(account: Account) = println(s"${account.number} updated...")
  }

  class JpaAccountRepository extends AccountRepository {
    def loadAccount(accNumber: String) = new Account(accNumber, 1000.00)

    def updateAccount(account: Account) = println(s"${account.number} updated...")
  }

  //-------------------------------------------------------------------------------------

  class TxrService(accountRepository: AccountRepository) {

    def txr(amount: Double, fromAccNum: String, toAccNum: String): Unit = {
      // load 'from' & 'to' accounts
      val fromAccount = accountRepository.loadAccount("1")
      val toAccount = accountRepository.loadAccount("2")

      // debit & credit

      // update both accounts
      accountRepository.updateAccount(fromAccount)
      accountRepository.updateAccount(toAccount)
    }
  }

  //-------------------------------------------------------------------------------------


  val txrService = new TxrService(new JpaAccountRepository)

  //  txrService.txr(100.00, "1", "2")


  //-------------------------------------------------------------------------------------


  trait Friend {
    val name: String

    def listen = println(s"im ${name} listening..")
  }

  def seekHelp(friend: Friend) = {
    friend.listen
  }


  class Person(val name: String) extends Friend

  abstract class Animal(val name: String)

  class Dog(name: String) extends Animal(name) with Friend

  class Cat(name: String) extends Animal(name)


  val peter = new Person("peter")
  val myDog = new Dog("puppy")
  val myCat = new Cat("Meow") with Friend
  val otherCat = new Cat("Meow1")

  seekHelp(peter)
  seekHelp(myDog)
  seekHelp(myCat)


  // traits vs abstract classes
  // 1 - traits do not have constructor parameters
  // 2 - multiple traits may be  inherited by the same class
  // 3 - traits = behavior, abstract class = "thing"


}
