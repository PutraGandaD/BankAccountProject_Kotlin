fun main() {
    println("Welcome to your banking system.")
    println("What type of account would you like to create?")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")

    var accountType: String = ""
    var userChoice: Int = 0

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
}