package com.radiance.mafiahelper

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bsvt.core.character.Character

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun View.click(startScale: Float, endScale: Float, duration: Long){
    val anim = ScaleAnimation(
        startScale, endScale,
        startScale, endScale,
        Animation.RELATIVE_TO_SELF, 0.5f,
        Animation.RELATIVE_TO_SELF, 0.5f
    )
    anim.duration = duration
    this.startAnimation(anim)
}

fun shift(array: ArrayList<Character>, position: Int): ArrayList<Character> {
    val answer = ArrayList<Character>()

    for (i in position until array.size){
        answer.add(array[i])
    }

    for (i in 0 until position){
        answer.add(array[i])
    }

    return answer
}

fun View.enter() {
    val valueAnimator = ValueAnimator.ofFloat(300f, 0f)
    valueAnimator.addUpdateListener {
        this.translationX = it.animatedValue as Float
    }
    valueAnimator.interpolator = LinearInterpolator()
    valueAnimator.duration = 200
    valueAnimator.start()
}

fun View.out() {
    val valueAnimator = ValueAnimator.ofFloat(0f, 300f)
    valueAnimator.addUpdateListener {
        this.translationX = it.animatedValue as Float
    }
    valueAnimator.interpolator = LinearInterpolator()
    valueAnimator.duration = 200
    valueAnimator.startDelay = 0
    valueAnimator.start()
}

fun Fragment.showFragment(@IdRes container: Int, fragment: Fragment) {
    activity?.supportFragmentManager?.let {
        it.beginTransaction()
            .add(container, fragment)
            .addToBackStack(null)
            .commit()
    }
}

fun Fragment.setUpToolbar(toolbar: Toolbar?, @StringRes title: Int) {
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    (activity as AppCompatActivity).supportActionBar?.setTitle(title)

    toolbar?.setNavigationOnClickListener {
        findNavController().popBackStack()
    }
}