package edu.temple.bookrecommendationsystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chaquo.python.Python

/*
A fragment class containing a search bar that will send a query to our database and return the top
results for the user to view.
 */

class SearchFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (! Python.isStarted()) {
            Python.start(Python.getPlatform());
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.search_results_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = SearchAdapter(arrayListOf()) {}

        requireView().findViewById<ImageButton>(R.id.search_button).setOnClickListener {
            val query = requireView().findViewById<EditText>(R.id.search_edit_text).text

            val py = Python.getInstance()
            val showTest = py.getModule("book_Search_refactored")
            val test = showTest.callAttr("driver")
            //TODO: make so user cannot put a new line in the search text box
            //TODO: call search w query. parse results and put array in place of Application.Singleton.dummyBooks below
            recyclerView.adapter = SearchAdapter(Application.Singleton.dummyBooks) {
                val fragment = BookDetailsFragment(0)
                val bundle = Bundle()
                bundle.putString("title", it.title)
                bundle.putString("author", it.author)
                bundle.putInt("cover", it.coverURL)
                fragment.arguments = bundle
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container1, fragment)
                    .addToBackStack(null)
                    .commit()
            }
            Toast.makeText(this.context, test.toString(), Toast.LENGTH_LONG).show()
        }
    }

}
