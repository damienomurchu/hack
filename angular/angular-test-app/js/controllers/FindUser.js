app.controller('FindUser', ['$scope', 'users', function($scope, users) {
  users.success(function(data) {
    $scope.users = data;
  });
  $scope.dropdownUser = '';
  $scope.foundUser = function(data) {
    for ( var i = 0; i < data.length; i ++) {
      if (data[i] === $scope.dropdownUser) {
        dropdownUser.push(data[i]);
      }
    }
  }
}]);

/*
app.controller('FindUser', ['$scope', function($scope, $http) {
  $scope.searchId = '';
  $scope.searchResults = findUserById();

  function findUserById() {
    $http.get('http://localhost:8000/users/' + $scope.searchId)
        .then(function(response) {
          return response;
        });
  }
}]);
*/