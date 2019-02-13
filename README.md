# Facebook Manager

This is a fancy project which builds an excel file containing a list of all Facebook friends and navigates all friends profile to follow or unfollow them as specified in that excel file.



### Specifications
The program can
* Login to Facebook using username and password and save cookies to a file.
* Login to Facebook using the cookie file, stored before.
* Go to friend list page of the user and scroll down until all profiles loaded.
* Scrap all profiles name, id etc. which are available.
* Save Scraped data to an excel file.
* Navigate every profile from the excel file, which `Change` column has the value `yes`.
* Click on 'follow' or 'unfollow' button, as specified (If not specified, then 'follow').

 
Under the `resource` directory, there is a file named `usr_psswd.txt`, which have to contain facebook username/email in the first line and password in the second line. Write your Facebook username to the first line and password at the second line in this file. This file is used when logging in for the first time or if cookies are expired. In the `src/main/java/com/jenin/FacebookManager/Main.java ` file there is a variable named `firstName` which default value is `Arafat`, change it to your facebook first name, which displayed at the top when you log in to Facebook.

When the program is run, an excel file named `Friend_list.xlsx` is created at the root of the project directory. The excel file contains 8 columns:

- **ID:** Numeric ID of a profile.
- **UserID:** Facebook ID.
- **Name:** Name of the profile.
- **Link:** Profile Link.
- **Status:** Previous follow/unfollow/see_first Status, default value is `null` (Accepted values are 'Default', 'Unfollow').
- **newStatus:** Current follow/unfollow/see_first Status, default value is `null` (Accepted values are 'Default', 'Unfollow').
- **Change:** It decides whether the new Status have to update or not, the default value is `yes`.
- **TimeStamp:** Last update time.

If `Friend_list.xlsx` file already exists then friend list generating is aborted, the previous file is used to navigate friends profile. You can edit the (Only `Status`, `newStatus`, `Change` column) excel file after generating it. 'newStatus' contains current follow/unfollow status, `Status` contains status before the last change. For example, if one's profile's current status is unfollowing, then the cell value of `newStatus` will be 'Unfollow'. If you change `newStatus` cell value to 'Default' and change cell to `yes`, then after the program run, the value of `Status` cell will be 'Unfollow', `newStatus` will be 'Default' and `Change` will be 'no'.

In `src/main/java/com/jenin/FacebookManager/NavigateFriend.java` file there is a variable named `bigDelayTime` which sets delay time when navigating profiles. Increase it if Facebook blocks your ID for fast browsing.


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


### Features
1. Login to Facebook.
2. Make a list of friends from Facebook.
3. Write an excel file containing the friend list with details.
4. Follow/unfollow friends from that excel file, as specified.


### Plans To-do
1. Make a friend request from a list.
2. Bring See_first option, it's not available now.
3. Make a list of post likers/commenter with details information of the profiles.
4. A robot which can reply with fixed text to the new messages.


