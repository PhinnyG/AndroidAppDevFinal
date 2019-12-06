package com.example.madlibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rand = randomNum()
        var story = stories[rand].toWords()
        var storyWords = storiesWords[rand].toWords()

        WordType.text = storyWords[0]
        var location = 0

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

        ContinueButton.setOnClickListener {
            ContinueButton.setVisibility(View.INVISIBLE)
            WordType.text = createStory(story, storyWords)
            WordType.setVisibility(View.VISIBLE)
            WordType.textSize = 20f
            HypeMessage.setVisibility(View.INVISIBLE)
            PlayAgainButton.setVisibility(View.VISIBLE)
        }

        PlayAgainButton.setOnClickListener {
            rand = randomNum()
            story = stories[rand].toWords()
            storyWords = storiesWords[rand].toWords()
            EnterButton.setVisibility(View.VISIBLE)
            Label.setVisibility(View.VISIBLE)
            WordField.setVisibility(View.VISIBLE)
            WordType.setVisibility(View.VISIBLE)
            WordType.text = storyWords[0]
            location = 0
            PlayAgainButton.setVisibility(View.INVISIBLE)
        }


    }

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

    fun randomNum(): Int {
        return (0..(stories.size-1)).random()
    }

    fun String.toWords() = trim().splitToSequence(' ').filter { it.isNotEmpty() }.toMutableList()
    val stories = mutableListOf("I wanna be a * just like my dad! But I don't want to have to * all day.", "One of the most * characters in fiction is named" +
            "\"Tarzan of the * .\" Tarzan was raised by a/an" +
            " * and lives in the * jungle in the" +
            " heart of darkest * . He spends most of his time" +
            " eating * and swinging from tree to * ." +
            " Whenever he gets angry, he beats on his chest and says," +
            "\" * !\" This is his war cry. Tarzan always dresses in" +
            " * shorts made from the skin of a/an * " +
            " and his best friend is a/an * chimpanzee named" +
            " Cheetah. He is supposed to be able to speak to elephants and" +
            " * . In the movies, Tarzan is played by * .")
    val storiesWords = mutableListOf("Job Verb", "adjective plural-noun noun adjective place plural-noun noun funny-noise adjective noun adjective plural-noun person's-name")
}