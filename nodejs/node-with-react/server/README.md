Feedback-API-Server
===

A Node.js API server used to manage feedback responses from users. 

**NOTE** the server uses google oauth for user login. Before running/ deploying the server you will need to setup google oauth on the server side. Do so by adding your `google client id` and `google client secret` credentials to the `config/keys.js.sample` file and then renaming the file to `config/keys.js`.