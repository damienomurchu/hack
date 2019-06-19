# Latest Flicks

## What is Latest Flicks

Latest Flicks is an android app that lets you check the details of latest movies.


## Requirements

- a valid api key for themoviedb.org


## Running the app

- download the app code:  
`git clone git@github.com:damienomurchu/latest-flicks.git`

- add your api key:
  - rename the file `keystore.properties.sample` (in the root of repo folder) to `keystore.properties`
  - replace `"<replace-this-with-your-api-key>"` with your api key (leave the quotes)

- execute `./gradlew installDebug` in the root of the project folder while you have a running emulator or android device attached
