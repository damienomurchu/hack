app.directive('userList', function() {
  return {
    restrict: 'E',
    scope: {
      info: '='
    },
    templateUrl: 'js/directives/userList.html'
  };
});