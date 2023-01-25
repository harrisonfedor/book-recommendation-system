package edu.temple.bookrecommendationsystem

/**
 * Book
 * this class will be used to encapsulate, transfer, and store the relevant book info for the app
 *
 * @property title - the book title
 * @property coverURL - the url of the image of the cover
 * @property id - the id of the book in the database
 * @constructor Create Book
 */
data class Book(val title: String, val coverURL: String, val id: Int)
