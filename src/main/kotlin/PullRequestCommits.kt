import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import schema.github.RetrieveUsernameQuery

suspend fun main() {
    val token = GitHubTokenAccessor.getTokenFromPropertyFile()
    val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain: Interceptor.Chain ->
                val original: Request = chain.request()
                val builder: Request.Builder = original.newBuilder().method(original.method(), original.body())
                builder.header("Authorization", "bearer $token")
                chain.proceed(builder.build())
            }
            .build()

    val apolloClient: ApolloClient = ApolloClient.builder()
            .serverUrl("https://api.github.com/graphql")
            .okHttpClient(okHttpClient)
            .build()
    
// TODO    val pullRequest = apolloClient.query(RetrievePullRequestCommits()).await()
//    print(user.data?.viewer?.login)
}
