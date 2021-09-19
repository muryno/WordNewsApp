package com.worldNews.app.utils

import android.content.Context
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment




import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

fun Fragment.safeNavigateFromNavController(directions: NavDirections) {
    val navController = findNavController()
    val destination = navController.currentDestination as FragmentNavigator.Destination
    if (javaClass.name == destination.className) {
        navController.navigate(directions)
    }
}


inline fun SearchView.onQueryTextChanged(crossinline onQueryTextChanged: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            onQueryTextChanged(query)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    })
}


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

inline fun <T : View> T.showIf(condition: (T) -> Boolean) {
    if (condition(this)) {
        show()
    } else {
        hide()
    }
}

inline fun <T : View> T.hideIf(condition: (T) -> Boolean) {
    if (condition(this)) {
        hide()
    } else {
        show()
    }
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    requireContext().showToast(message, duration)
}