miGire.factory('PedidoResource', function($resource) {
	return $resource('pedidoLista');
});


miGire.config(function($routeProvider) {
	$routeProvider.when('/pedido/lista', {
		templateUrl : 'pedido/pedidoLista.html',
		controller : 'PedidoListaCtrl as ctrl'
	})
});




miGire.controller('PedidoListaCtrl', function(PedidoResource, msgDialog, $log, $http){
		
});


