
var notificacionService=angular.module('user.service', []);

notificacionService.factory('$userAuthenticationModel', function($http) {
	
	var user= {
		userAuthentication:null,
			
		loadUser : function () {
			var self 	= this;
			$http({method: 'GET', 
				url: 'login/user'}
			).success(function(data, status, headers, config) {
				self.userAuthentication=data;
			});
		}
	}; 
	
	return user;
	
	
});


