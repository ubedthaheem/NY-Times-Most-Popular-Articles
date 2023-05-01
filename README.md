
# NY Times Most Popular Articles

This is an Android app that fetches and displays the most popular articles from The New York Times API. The app uses Retrofit to make API calls and displays the article information in a RecyclerView.

## Prerequisites
Before running the app, make sure you have the following:

* Android Studio 4.1 or later
* Java Development Kit (JDK) 8 or later
* A valid API key for The New York Times API. You can get one by registering [here](https://developer.nytimes.com/get-started)

## Getting Started
1. Clone the repository to your local machine using:
```javascript
git clone https://github.com/<username>/nytimes-most-popular-articles.git
```
2. Open Android Studio and select "Open an existing Android Studio project" from the main menu. Browse to the cloned repository directory and select the nytimes-most-popular-articles directory.

3. In the Constants.java file, replace the API_KEY constant with your own API key.

4. Build and run the app.

## Features
* Fetches the most popular articles from The New York Times API using Retrofit.
* Displays the article information (title, author, date, and thumbnail) in a RecyclerView.
* Allows the user to click on an article to open it in a web browser.

## Libraries Used
* [Retrofit](https://square.github.io/retrofit/) - for making API calls
* [Glide](https://github.com/bumptech/glide) - for loading and caching images
* [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview) - for displaying a list of articles

## License
This project is licensed under the MIT License - see the [LICENSE](https://chat.openai.com/LICENSE) file for details.
