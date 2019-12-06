package com.example.madlibs

//defines the index of the story that is chosen
var index = -1

//sets index to a random number
fun setRandom() {
    index = randomNum()
}

//returns a story based on a random number generated
//sets the index for the story
fun getStory(): MutableList<String> {
    setRandom()
    return stories[index].toWords()
}

//returns the words that the user will be prompted for
//must be called after getStory() in order to return the correct index
fun getStoryWords(): MutableList<String> {
    return storiesWords[index].toWords()
}

//converts a string into a list of words
fun String.toWords() = trim().splitToSequence(' ').filter { it.isNotEmpty() }.toMutableList()

//returns a random number between 0 and the amount of stories in data minus 1
fun randomNum(): Int {
    return (0..(stories.size-1)).random()
}

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