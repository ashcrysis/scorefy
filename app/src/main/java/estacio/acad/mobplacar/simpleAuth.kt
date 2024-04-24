package estacio.acad.mobplacar

class simpleAuth {
    private val usernames: MutableList<String> = mutableListOf()
    private val passwords: MutableList<String> = mutableListOf()

    fun addUser(username: String, password: String) {
        usernames.add(username)
        passwords.add(password)
    }

    fun verifyCredentials(username: String, password: String): Boolean {
        val index = usernames.indexOf(username)
        return if (index != -1 && index < passwords.size) {
            passwords[index] == password
        } else {
            false
        }
    }
}