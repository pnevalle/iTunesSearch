# iTunesSearch
An application displaying a list of search results from [iTunes Store API](https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/).

For this project, this [endpoint](https://itunes.apple.com/search?term=star&amp;country=au&amp;media=movie) was specifically used to obtain the data.

Here are the features of the app:
1. Display the list of results from the API
2. Save the API search results into the database. This is used since the endpoint is static and to serve as cache and to also feature offline mode.
3. The database will always be the source of the data. For the first run, it will fetch from the API.
4. To update or refresh the database, pull to refresh the list to invoke an API call.
5. The last network call or update is displayed in the header of the list. This is also to showcase the implementation of persistence.
6. Clicking the list will display a detail screen.
7. Shared element transition between the list and detail was implemented.

This project was designed using clean architecture and MVVM pattern. The main advantage of this is it promotes separation of concerns and will be easier
for testing and maintenance. The disadvantage of this is more code and classes will be needed and will take some time to get used to.

Upon making the project, here are some of the components that are explored and used within the application:
1. [Retrofit](https://square.github.io/retrofit/) - a type-safe HTTP client for Android
2. [Room](https://developer.android.com/jetpack/androidx/releases/room?gclid=Cj0KCQiAzfuNBhCGARIsAD1nu-81YN2QNGCWDepnNV40OxQUNRrQBGtm3EzuJ-f7DYA1T2ff_6cKuxoaAtaoEALw_wcB&gclsrc=aw.ds) - Android Jetpack persistence library
3. [Picasso](https://square.github.io/picasso/) - image downloading and caching library for Android
4. [DataStore](https://developer.android.com/topic/libraries/architecture/datastore?hl=en) - data storage used to store key-value pairs
5. [Flow](https://developer.android.com/reference/kotlin/java/util/concurrent/Flow?hl=en) - kotlin coroutine to handle the stream of data asynchronously 
6. [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation?hl=en) - framework for navigating between 'destinations' within an Android application

# Credits
1. https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/
2. https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
3. https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
4. https://www.raywenderlich.com/8279305-navigation-component-for-android-part-3-transition-and-navigation
5. https://mikescamell.com/shared-element-transitions-part-4-recyclerview/
6. https://square.github.io/picasso/
7. https://square.github.io/retrofit/
