var app = angular.module('UsersApp', ['ngRoute']);

app.config(function ($routeProvider) {
  $routeProvider
      .when('/findUser', {
        //controller: 'HomeController',
        templateUrl: 'views/findUser.html'
      })
      .when('/createUser', {
        controller: 'CreateUser',
        templateUrl: 'views/createUser.html'
      })
      .when('/deleteUser', {
        //controller: 'HomeController',
        templateUrl: 'views/deleteUser.html'
      })
      .when('/userList',  {
        controller: 'UserController',
        templateUrl: 'views/userList.html'
      })
      .otherwise({
        redirectTo: '/'
      });
});

// filter to capitalise initial letter in each word in a string
app.filter('capitalise', function() {
  return function(x) {

    return x.split(" ").map(function(i) {
      return i[0].toUpperCase() + i.substring(1)}).join(" ");

  };
});
