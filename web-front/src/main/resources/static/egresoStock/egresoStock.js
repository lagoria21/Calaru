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
	
	var self = this;
	self.egreso = EgresoStockResource.query();
	
	self.p = self.egreso.responsable;

	
	
	
	self.filtro = {};
	
	self.limpiarFiltro = function() {
		self.limpiarSeleccion();
		self.filtro = {};
		self.reloadPage = true;
	}
	
	self.cambioFiltro = function() {
		self.reloadPage = true;
	}	

	self.limpiarSeleccion = function() {
		self.seleccionados = new Immutable.Set();
		self.invertirSeleccion = false;
	}
	
	self.limpiarSeleccion();
	
	
});


