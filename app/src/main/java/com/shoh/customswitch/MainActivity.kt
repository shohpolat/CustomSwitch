package com.shoh.customswitch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.Transition
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    var isUsd = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currency.setOnClickListener {
            CoroutineScope(Main).launch {
                if (isUsd){
                    isUsd = false
                    check_Uzs()
                    uzs_off.fadeOut()
                    usd_off.fadeIn()
                    delay(100)
                    currency.setImageResource(R.drawable.ic_uzs_on)
                    delay(100)
                    uzs_off.visibility = View.GONE
                    usd_off.visibility = View.VISIBLE

                }
                else {
                    isUsd = true
                    check_Usd()
                    usd_off.fadeOut()
                    uzs_off.fadeIn()
                    delay(100)
                    currency.setImageResource(R.drawable.ic_usd_on)
                    delay(100)
                    usd_off.visibility = View.GONE
                    uzs_off.visibility = View.VISIBLE
                }
            }


        }

        uzs_off.setOnClickListener {
            if (isUsd){
                isUsd = false
                check_Uzs()
                uzs_off.visibility = View.GONE
                usd_off.visibility = View.VISIBLE
            }
            else {
                isUsd = true
                check_Usd()
                usd_off.visibility = View.GONE
                uzs_off.visibility = View.VISIBLE
            }
        }
        usd_off.setOnClickListener {
            if (isUsd){
                isUsd = false
                check_Uzs()
                uzs_off.visibility = View.GONE
                usd_off.visibility = View.VISIBLE
            }
            else {
                isUsd = true
                check_Usd()
                usd_off.visibility = View.GONE
                uzs_off.visibility = View.VISIBLE
            }
        }

    }

    fun check_Uzs(){
        val constraintLayout = root_2
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)

        constraintSet.connect(
            R.id.currency,
            ConstraintSet.RIGHT,
            R.id.root_2,
            ConstraintSet.RIGHT,
            0
        )

        constraintSet.clear(
            R.id.currency,
            ConstraintSet.LEFT,
        )

        val transition: Transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 200
        TransitionManager.beginDelayedTransition(root_2, transition)

        constraintSet.applyTo(constraintLayout)
    }

    fun check_Usd(){
        val constraintLayout = root_2
        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)

        constraintSet.connect(
            R.id.currency,
            ConstraintSet.LEFT,
            R.id.root_2,
            ConstraintSet.LEFT,
            0
        )

        constraintSet.clear(
            R.id.currency,
            ConstraintSet.RIGHT,
        )

        val transition: Transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(1.0f)
        transition.duration = 200
        TransitionManager.beginDelayedTransition(root_2, transition)

        constraintSet.applyTo(constraintLayout)

    }

    fun AppCompatImageView.fadeIn(){
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator() //add this
        fadeIn.startOffset = 0
        fadeIn.duration = 200
        val animation = AnimationSet(false) //change to false
        animation.addAnimation(fadeIn)
        this.animation = animation
    }

    fun AppCompatImageView.fadeOut(){
        val fadeOut = AlphaAnimation(1f, 0f)
        fadeOut.interpolator = DecelerateInterpolator() //and this
        fadeOut.startOffset = 100
        fadeOut.duration = 200

        val animation = AnimationSet(false) //change to false
        animation.addAnimation(fadeOut)
        animation.addAnimation(fadeOut)
        this.animation = animation
    }

}