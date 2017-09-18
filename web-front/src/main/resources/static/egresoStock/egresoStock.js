miGire.factory('EgresoStockResource', function($resource) {
	return $resource('egresos');
});


miGire.config(function($routeProvider) {
	$routeProvider.when('/egresoStock/lista', {
		templateUrl : 'egresoStock/egresoStock.html',
		controller : 'EgresoStockListaCtrl as ctrl'
	})
});


miGire.controller('EgresoStockListaCtrl', function(EgresoStockResource, msgDialog, $log, $http){
	
		
});


