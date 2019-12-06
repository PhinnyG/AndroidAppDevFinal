package com.example.madlibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var story = getStory()
        var storyWords = getStoryWords()

        WordType.text = storyWords[0]
        var location = 0

        //stores the users input into the index of the story words array that was being prompted for
        //changes layout of screen if there are no more words to be prompted for
        EnterButton.setOnClickListener {
            if (WordField.text.toString().equals("")) {
                WordField.hint = "ENTER WORD"
            }
            else {
                location+=1
                if (location < storyWords.size)
                    WordType.text = storyWords.get(location)
                storyWords[location-1] = WordField.text.toString()
                WordField.setText(null)
                WordField.hint = "Enter word here"
            }
            if (location == storyWords.size) {
                EnterButton.setVisibility(View.INVISIBLE)
                ContinueButton.setVisibility(View.VISIBLE)
                Label.setVisibility(View.INVISIBLE)
                WordField.setVisibility(View.INVISIBLE)
                WordType.setVisibility(View.INVISIBLE)
                HypeMessage.setVisibility(View.VISIBLE)
            }
        }

        //reveals the completed madlib as text to the user
        ContinueButton.setOnClickListener {
            ContinueButton.setVisibility(View.INVISIBLE)
            WordType.text = createStory(story, storyWords)
            WordType.setVisibility(View.VISIBLE)
            WordType.textSize = 20f
            HypeMessage.setVisibility(View.INVISIBLE)
            PlayAgainButton.setVisibility(View.VISIBLE)
        }

        //resets the madlib screen to prompt for a new story
        PlayAgainButton.setOnClickListener {
            story = getStory()
            storyWords = getStoryWords()
            EnterButton.setVisibility(View.VISIBLE)
            Label.setVisibility(View.VISIBLE)
            WordField.setVisibility(View.VISIBLE)
            WordType.setVisibility(View.VISIBLE)
            WordType.text = storyWords[0]
            location = 0
            PlayAgainButton.setVisibility(View.INVISIBLE)
        }


    }

    //uses array logic to create a string that is the story with the user input words
    fun createStory(story: MutableList<String>, words: MutableList<String>): String {
        var location = 0
        var finished = ""
        story.forEach() {
            if(it.equals("*")) {
                finished += words[location] + " "
                location++
            }
            else {
                finished += it + " "
            }
        }
        return finished
    }
}