package com.worldNews.app.utils


import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.worldNews.app.App
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Fragment.safeNavigateFromNavController(directions: NavDirections) {
    val navController = findNavController()
    val destination = navController.currentDestination as FragmentNavigator.Destination
    if (javaClass.name == destination.className) {
        navController.navigate(directions)
    }
}


fun CurrentDate(): String {
    val now = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mm")
    return now.format(formatter)
}


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}



fun ShowToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(App.instance?.applicationContext, message, duration).show()
}
