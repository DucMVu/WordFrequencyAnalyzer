# Project Title

Simple project to count word frequency in a sentence. Symbols and case sensitivity are not considered.

## Getting Started

This project is built with Sring Boot.

### Dependencies and plugins

* spring-boot-starter-web
* spring-boot-starter-test
* spring-boot-maven-plugin

### Installing

* Get this project using the command line
```
git clone https://github.com/DucMVu/WordFrequencyAnalyzer
```

### Executing program

* Run the application. If successfully built, port 8080 is commonly used for testing.
* Available API endpoints are listed below:
```
/api/highestFrequency to check the highest frequency of words in a sentence. Input param is sentence.
For example, "This is a test Test text." returns "2"
```

```
/api/wordFrequency to check the frequency of a given word in a sentence. Input params are sentence and word.
For example, frequency of "test" in "This is a test Test text." sentence is "2".
Frequency of "text" is "1".
Frequency of "notfound" is "0".
```

```
/api/mostFrequentWords to check the frequencies of a number of most repeated words in a sentence. Input params are sentence and number.
For example, frequencies of two most repeated words in "This is a test Test text. Test is hard. Test is fun" is "[{"test":4},{"is":3}]".
Frequency of most repeated word is "[{"test":4}]".
```