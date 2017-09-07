var app = angular.module('app', []);

app.factory('$UserService',['$http','$window','$timeout' ,function($http,$window) {
	
	var self = this;
	
	var service= {};

	service.authenticate = function (name, password, enterprise,funtionError) {
		
		var req = {
			method: 'POST', 
			url: 'login/authenticate',
		
			data:{'username': name,'password': password,'enterpriseSelected':enterprise}
		    
			
		}
		
		self.nombre = 'alberto';
		self.pass = 'alberto';
		
		if(self.nombre == username && self.pass == password)
			{
			
			}
		
		$window.location.href='index.html';
//		$http(req).success(function(data, status, headers, config) {
//				if(data.resultado==0){
//					$window.location.href='index.html';
//				}else{
//					funtionError(data);
//				}
//		});
	}; 
	
	return service;
}]);


app.controller('loginController',['$scope','$window','$UserService','$timeout', function($scope,$window,$UserService,$timeout) {
	
	
	var user ={
			
		error 				: null,	
		username			: null,
		password			: null,
		enterpriseSelected	: null,
			
		authenticate:function() {
			var self 	= this;
			self.error 	= null;
			var idEnterprise = self.enterpriseSelected==null ? null:self.enterpriseSelected.id;
			$UserService.authenticate(self.username,self.password,idEnterprise,
					function(authenticationResult) {
				
				if(authenticationResult.resultado==5){
					$scope.enterprises = authenticationResult.data;
				}else{
					self.error = authenticationResult.descripcion;
					 $timeout(function() {
						 self.error =null;

				        }, 7000);
				}

			});
		},
		clear: function(){
			var self 				= this;
			
			self.error 				= null;	
			self.username			= null;
			self.password			= null;
			self.enterpriseSelected	= null;
			
			$scope.enterprises 		= null;
		}
	};
	
	$scope.user = user;
	
}]);



