package com.twitternurtelekom.network

import com.twitternurtelekom.model.Tweet
import retrofit2.Call
import retrofit2.http.*


interface TwitterService {

    /**
     * Updates the authenticating user's current status, also known as tweeting.
     *
     *
     * For each update attempt, the update text is compared with the authenticating user's recent
     * tweets. Any attempt that would result in duplication will be blocked, resulting in a 403
     * error. Therefore, a user cannot submit the same status twice in a row.
     *
     *
     * While not rate limited by the API a user is limited in the number of tweets they can create
     * at a time. If the number of updates posted by the user reaches the current allowed limit this
     * method will return an HTTP 403 error.
     *
     * @param status (required) The text of your status update, typically up to 140 characters. URL
     * encode as necessary. [node:840,title="t.co link wrapping"] may effect character
     * counts. There are some special commands in this field to be aware of. For
     * instance, preceding a message with "D " or "M " and following it with a screen
     * name can create a direct message to that user if the relationship allows for
     * it.
     */
    @FormUrlEncoded
    @POST("/1.1/statuses/update.json")
    fun saveTweet(@Field("status") status: String): Call<Any>

    /**
     * @param count (optional) Specifies the number of records to retrieve. Must be less than
     * or equal to 200. Defaults to 20. The value of count is best thought of as a limit to the
     * number of tweets to return because suspended or deleted content is removed after
     * the count has been applied.
     * @param lastTweetId (optional) Returns results with an ID less than (that is, older than)
     * or equal to the specified ID.
     */
    @GET("/1.1/statuses/home_timeline.json")
    fun fetchTweets(@Query("count") count: Int = 20,
                    @Query("max_id") lastTweetId: String? = null): Call<List<Tweet>>
}