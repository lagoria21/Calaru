var miGire = angular.module('MiGire', [ 'ngRoute', 'ngResource', 'gire.messageDlg', 'ngMessages' ,'gire.messagePregunta',
		'bgf.paginateAnything','user.service' ]);

convertDateInterceptor(miGire);

miGire.config(function($routeProvider) {
	$routeProvider.when('/home', {
		templateurl : 'home',
		controller : 'HomeCtrl'
	});
});


miGire.run(function($rootScope,$userAuthenticationModel) {
	$rootScope.userModal = $userAuthenticationModel;
	$rootScope.userModal.loadUser();
});
