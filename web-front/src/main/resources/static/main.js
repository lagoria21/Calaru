var miGire = angular.module('MiGire', [ 'ngRoute', 'ngResource', 'gire.messageDlg', 'ngMessages',
		'bgf.paginateAnything','user.service' ]);

convertDateInterceptor(miGire);

miGire.config(function($routeProvider) {
	$routeProvider
	.when('/home', {
		templateurl : '/home',
		controller : 'HomeCtrl'

	});
});

miGire.constant("EstadosTramite", {EP: 'En Proceso', F: 'Finalizado'});

miGire.filter('estadoFmt', function(EstadosTramite) {
	//var estadosTramite = {EP: 'En Proceso', F: 'Finalizado'};
	return function(estadoTramite) {
		return estadoTramite ? EstadosTramite[estadoTramite] : "<Nulo>";  
	}
});


//miGire.run( function($rootScope,$http,notificacionDialog,$userAuthenticationModel,$notificacionModel) {
//	
//	$rootScope.userModal = $userAuthenticationModel;
//	$rootScope.userModal.loadUser();
//	
//	$rootScope.notification = $notificacionModel;
//	$rootScope.notification.findAllAndShowNotification();
//});
//

