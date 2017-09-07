miGire.factory('EgresoStockResource', function($resource) {
	return $resource('egresoStock');
});


miGire.config(function($routeProvider) {
	$routeProvider.when('/egresoStock/lista', {
		templateUrl : 'egresoStock/egresoStock.html',
		controller : 'EgresoStockListaCtrl as ctrl'
	})
});


//miGire.factory('EgresoStockResource', function($resource) {
//	return $resource('stock/:id', {id: '@id', completo: true}, {
//		'update' : {method : 'PUT'},
//		'deleteList' : {method : 'DELETE'}
//	});
//});


miGire.controller('EgresoStockListaCtrl', function(EgresoStockResource, msgDialog, $log, $http){
		
});


