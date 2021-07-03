# Testist
[![Build Status](https://travis-ci.org/aistomin/testist.svg?branch=master)](https://travis-ci.org/aistomin/testist)
[![Hits-of-Code](https://hitsofcode.com/github/aistomin/testist)](https://hitsofcode.com/github/aistomin/testist/view)
[![codecov](https://codecov.io/gh/aistomin/testist/branch/master/graph/badge.svg)](https://codecov.io/gh/aistomin/testist)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.aistomin/testist/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.aistomin/testist)
[![javadoc](https://javadoc.io/badge2/com.github.aistomin/testist/javadoc.svg)](https://javadoc.io/doc/com.github.aistomin/testist)

Library with some basic classes that allows to create a testing applications for students.

## Getting Started

### System Requirements
- JDK 8 or higher.
- Apache Maven 3.3.9 or higher

### Add Maven Dependency
If you use Maven, add the following configuration to your project's `pom.xml`
```maven
<dependencies>
    <!-- other dependencies are there -->
    <dependency>
        <groupId>com.github.aistomin</groupId>
        <artifactId>testist</artifactId>
        <version>0.2</version>
    </dependency>
    <!-- other dependencies are there -->
</dependencies>
```
or, if you use `Gradle`, add the following line to your build file:
```
compile 'com.github.aistomin:testist:0.2'
```

## Licence
The project is licensed under the terms of the
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html).

## Have You Found a Bug? Do You Have Any Suggestions?
Although we try our best, we're not robots and bugs are possible :) Also we're
always happy to hear some suggestions, ideas, thoughts from you. Don't hesitate
to [create an issue](https://github.com/aistomin/testist/issues/new).
It will help us to make our project better. Thank you in advance!!!

## How to Contribute
Do you want to help us with the project? We will be more than just happy.
Please: fork the repository, make changes, submit a pull request. We promise
to review your changes in the next couple of days and merge them to the master
branch, if they look correct. To avoid frustration, before sending us your pull
request please run full Maven build:

```
$ mvn clean install package javadoc:javadoc
```
Keep in mind our [system requirements](#system-requirements).
