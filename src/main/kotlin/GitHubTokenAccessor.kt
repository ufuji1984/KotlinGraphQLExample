import java.io.File
import java.io.FileInputStream
import java.util.*

class GitHubTokenAccessor {
    companion object {
        fun getTokenFromPropertyFile(): String {
            // https://www.techiedelight.com/read-write-values-properties-file-kotlin/
            val file = File("/Users/yu-fujisaki/git/KotlinGraphQLExample.properties")

            val prop = Properties()
            FileInputStream(file).use { prop.load(it) }

//            // Print all Properties
//            prop.stringPropertyNames()
//                .associateWith {prop.getProperty(it)}
//                .forEach { println(it) }
            return prop.getProperty("token")
        }
    }
}