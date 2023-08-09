fun main() {
    var accountType: String = "" // account type
    var userChoice: Int = 0 // user choice

    println("Welcome to your banking system.")
    println("What type of account would you like to create?")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")

    val money: Int = (0..1000).random() // generate random money
    var output: Int = 0 // temporary variable for testing printed output
    var accountBalance = 0 // initialize account balance variable

    while (accountType == "") {
        println("Which option do you choose? (1, 2, or 3)")
        userChoice = (1..5).random()
        println("The selected option is $userChoice")
        when (userChoice) {
            1 -> {
                accountType = "debit"
                accountBalance = (0..1000).random()
                println("You have created a $accountType account.")
                println("Your current balance is $accountBalance dollars")
            }
            2 -> {
                accountType = "credit"
                accountBalance = (-1000..0).random() // credit bank account is always in 0 or in negative numbers.
                println("You have created a $accountType account.")
                println("Your payment due is $accountBalance dollars")
            }
            3 -> {
                accountType = "checking"
                accountBalance = (0..1000).random()
                println("You have created a $accountType account.")
                println("Your current balance is $accountBalance dollars")
            }
            else -> continue // repeat the loop when else condition is reached
        }
    }

    // Function to withdraw money from Checking and credit bank accounts
    fun withdraw(amount: Int) : Int {
        println("The amount of money you requested for withdraw is $amount dollars")
        accountBalance -= amount
        //println("Your account balance now is $accountBalance dollars")
        return amount
    }

    // Function to withdraw money from debit
    fun debitWithdraw(amount: Int) : Int {
        if (accountBalance == 0) {
            println("Your current balance is $accountBalance. Withdraw cannot be processed")
            return accountBalance
        } else {
            if (amount > accountBalance) {
                println("The amount of money you requested for withdraw is $amount dollars")
                println("Your account balance is less than amount you requested for withdraw. Withdraw cannot be processed")
                return 0
            } else {
                return withdraw(amount)
            }
        }
    }

    // Function to deposit for checking and debit account
    fun deposit(amount: Int) : Int {
        println("The amount of money you requested for deposit is $amount")
        accountBalance += amount
        return amount
    }

    // Function to deposit for credit account
    fun creditDeposit(amount: Int) : Int {
        println("The amount of money you requested for deposit is $amount")
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

    fun transfer(mode: String) {
        val transferAmount: Int
        when(mode) {
            "withdraw" -> {
                when(accountType) {
                    "debit" -> debitWithdraw(money)
                    "checking" -> {
                        if (money > accountBalance) {
                            println("Your account balance is less than amount you requested for withdraw. Withdraw cannot be processed")
                        } else {
                            transferAmount = withdraw(money)
                            println("The amount you withdrew is ${transferAmount} dollars.")
                        }
                    }
                    "credit" -> withdraw(money)
                }
            }
            "deposit" -> {
                transferAmount = if (accountType == "credit") creditDeposit(money) else deposit(money)
                println("The amount you deposited is $transferAmount dollars.")
            }
            else -> return
        }
    }

    // TEST : Withdraw money from Checking and credit bank accounts
    //output = withdraw(money)
    //println("You've successfully withdrew $output dollars from your debit card.")

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
    //creditDeposit(money)

    println()
    println("What would you like to do?")
    println("1. Check bank account balance")
    println("2. Withdraw money")
    println("3. Deposit money")
    println("4. Close the app")

    var isSystemOpen = true
    var option = 0

    while(isSystemOpen) {
        println("Which option do you choose? (1, 2, 3, or 4)")
        option = (1..5).random()
        println("The selection option is $option")
        when(option) {
            1 -> {
                println("The current balance is $accountBalance dollars")
            }
            2 -> {
                transfer("withdraw")
                if (accountType == "credit") {
                    println("Your payment due now is $accountBalance dollars.")
                } else {
                    println("Your account balance now is $accountBalance dollars.")
                }
            }
            3 -> {
                transfer("deposit")
                if (accountType == "credit") {
                    println("Your payment due now is $accountBalance dollars.")
                } else {
                    println("Your account balance now is $accountBalance dollars.")
                }
            }
            4 -> {
                isSystemOpen = false
                println("The system is closed")
            }
            else -> continue
        }
    }
}