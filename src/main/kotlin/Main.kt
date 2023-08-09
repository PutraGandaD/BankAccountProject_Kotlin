fun main() {
    println("Welcome to your banking system.")
    println("What type of account would you like to create?")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")

    var accountType: String = "" // account type
    var userChoice: Int = 0 // user choice

    val money: Int = (0..1000).random() // generate random money
    var output: Int = 0 // temporary variable for testing printed output

    // Always work for Debit and Checking account
    //var accountBalance: Int = (1..1000).random()

    /*
     This is for Credit account.
     You need to make sure the account balance is always 0
     or in negative numbers in order to "simulate" real
     credit bank account. That's why i created separate code
     for this one.

     Use code below to simulate credit bank account balance,
     especially for creditDeposit function.
     */
    var accountBalance = -(0..1000).random()

    while (accountType == "") {
        userChoice = (1..5).random()
        println("The selected option is $userChoice")
        when (userChoice) {
            1 -> accountType = "debit"
            2 -> accountType = "credit"
            3 -> accountType = "checking"
            else -> continue // repeat the loop when else condition is reached
        }
    }
    println("You have created a $accountType account.")
    println("Your current balance is $accountBalance dollars")

    // Function to withdraw money from Checking and credit bank accounts
    fun withdraw(amount: Int) : Int {
        accountBalance -= amount
        println("You successfully withdrew $amount dollars")
        println("Your current balance is $accountBalance dollars")
        return amount
    }

    // Function to withdraw money from debit
    fun debitWithdraw(amount: Int) : Int {
        println("Amount of money you requested for withdraw = $amount dollars")
        if (accountBalance == 0) {
            println("Your current balance is $accountBalance. Withdraw cannot be processed")
            return accountBalance
        } else {
            if (amount > accountBalance) {
                println("Your account balance is less than amount you requested for withdraw. Withdraw cannot be processed")
                return 0
            } else {
                return withdraw(amount)
            }
        }
    }

    // Function to deposit for checking and debit account
    fun deposit(amount: Int) : Int {
        accountBalance += amount
        println("You deposited $amount dollars to your account")
        println("Your current account balance is $accountBalance dollars")
        return amount
    }

    // Function to deposit for credit account
    fun creditDeposit(amount: Int) : Int {
        println("The amount of money you request to deposit is $amount dollars")
        if (accountBalance == 0) {
            println("You don't need to deposit anything in order to pay off the account since it has already been paid off")
            return accountBalance
        } else {
            if (accountBalance + amount > 0) {
                println("Deposit failed, you tried to pay off an amount greater than the credit balance. The checking balance is $accountBalance dollars.")
                return 0
            } else {
                if (amount == -accountBalance) {
                    accountBalance = 0
                    println("You have paid off this account!")
                    return amount
                } else {
                    return deposit(amount)
                }
            }
        }
    }

    // TEST : Withdraw money from Checking and credit bank accounts
    //withdraw(money)

    // TEST : Withdraw money from debit
    //output = debitWithdraw(money)
    //println("You've successfully withdrew $output dollars from your debit card.")

    // TEST : Deposit money for checking and debit account
    //deposit(money)

    // TEST : Deposit money for credit account
    /*
     Please pay attention to accountBalance value (check the code above, scroll a bit).
     I made a separate accountBalance code where it's going to always generate
     in negative numbers to simulate a real credit bank account deposit.
     */
    creditDeposit(money)
}