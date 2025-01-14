package edu.temple.bookrecommendationsystem


import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Picasso


/**
 * Application
 * Contains a Singleton Object to store global variables to use throughout the application
 *
 * access pattern:
 * Application.Singleton.[wantToRead/previouslyRead/searchResults/recommendations]
 *
 * @constructor Create empty Application
 */
class Application {
    object Singleton {
        var wantToRead = ArrayList<Book>()
        var previouslyRead = ArrayList<Book>()
        var searchResults = ArrayList<Book>()
        var recommendations = ArrayList<Book>()
        var recIndex = 0

        /**
         * Csv to book array
         * Converts a comma separated String generated by a python file
         * into a Book object and adds it to an ArrayList
         *
         * @param csv - a comma separated String
         * @return an ArrayList of Book objects
         */
        fun csvToBookArray(csv: String) : ArrayList<Book> {
            //TODO: make this work for IDs
            val books = ArrayList<Book>()
            var url : String
            val elements = csv.replace("[0-9]+,".toRegex(),"").split(",http","\n")
            Log.d("Elements: ", elements.toString())
            for(i in 3 until elements.size - 1 step 2) {
                url = "http" + elements[i+1]
                books.add(Book(elements[i], url, 0)) //TODO: remove temp id of 0
            }
            return books
        }

        //TODO: write ArrayList<Book> to csv function

        /**
         * Loads an image from a url
         *
         * @param url - A url for an image of the book cover in the form of a String
         * @param imageView - the imageView into which the book cover image is to be placed
         */
        fun loadImage(url: String, imageView: ImageView) {
            Picasso.get().load(url).into(imageView)
        }
    }
}