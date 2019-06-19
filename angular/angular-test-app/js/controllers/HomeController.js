app.controller('UserController', ['$scope', 'users', '$routeParams', function($scope, photos, $routeParams) {
  users.success(function(data) {
    $scope.detail = data[$routeParams.id];
  });
}]);


