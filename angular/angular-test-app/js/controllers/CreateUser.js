app.controller('CreateUser', function($scope, $http) {

  $scope.createUser = function() {

    // create user data object
    var user = {
      name : {
        first : $scope.firstName,
        last : $scope.lastName,
      },
      email : $scope.email,
      password : $scope.password
    }

    // post new user to db via api
    $http.post('http://localhost:8000/users', user)
        .success(function(data) {
          console.log('New user created:\n' + data);
          //return data;
          $scope.createdUser = data;
        })
        .error(function(data) {
          console.log('Failed to create user:\n' + data);
        });

    // clear form fields
    $scope.firstName = '';
    $scope.lastName = '';
    $scope.email = '';
    $scope.password = '';
  }

});







