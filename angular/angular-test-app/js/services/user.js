app.factory('users', ['$http', function($http) {
  var getUsers = function() {
    return $http.get('http://localhost:8000/users')
        .success(function(data) {
          return data;
        })
        .error(function(data) {
          return data;
        });
  }


}]);