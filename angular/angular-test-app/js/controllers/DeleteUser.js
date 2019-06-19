app.controller('DeleteUser', ['$scope', '$http', 'users', function($scope, $http, users) {

  $scope.selectedUser = '';

  $scope.selectUser = function() {

  }

  users.success(function(data) {
    $scope.users = data;
  });

  $scope.deleteUser = function() {
    $http.delete('http://localhost:8000/users/' + $scope.selectedUser)
        .success(function(data) {
          console.log('User: ' + $scope.selectedUser + ' deleted');
          console.log('returned data: ' + data);

        })
        .error(function(data) {
          console.log('Failed to delete user: ' + $scope.selectedUser);
        });
  }

}]);