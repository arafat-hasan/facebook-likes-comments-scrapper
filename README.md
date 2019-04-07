# FacebookLikesComments



### Technologies used
* [Java](https://www.java.com/en/download/)
     * [Apache poi](https://poi.apache.org/)
* [Selenium Webdriver](https://www.seleniumhq.org/)
     * [Bonigarcia webdrivermanager](https://github.com/bonigarcia/webdrivermanager)
     * Headless drivers are used, it can be run in servers, using Jenkins, smoothly.
* [Maven](https://maven.apache.org/)

### Run Using Maven
Maven run command:
```sh
mvn exec:java -Dexec.mainClass="com.jenin.FacebookLikesComments.Main" -Dexec.cleanupDaemonThreads=false
```


### Difficulties
1. When it's browsed very fast, Facebook may block the user.
2. It's not the perfect  professional way to do such task, instead of using APIs (It's a fancy project 😀).
