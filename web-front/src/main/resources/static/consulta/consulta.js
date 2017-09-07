miGire.factory('ConsultaStockResource', function($resource) {
	return $resource('consultaStock');
});


miGire.config(function($routeProvider) {
	$routeProvider.when('/consulta/lista', {
		templateUrl : 'consulta/consultaLista.html',
		controller : 'ConsultaListaCtrl as ctrl'
	});
});



miGire.controller('ConsultaListaCtrl', function(ConsultaStockResource, msgDialog, $log, $http){
	var self = this;
	self.consulta = ConsultaStockResource.query();
	
	self.pdf = function(consulta)
	{
	  var doc = new jsPDF();
	  /*rigth, top*/
	  doc.setFontSize(22);
	  doc.text(50, 10,'FORMULARIO DE CONSULTA');
	  doc.text(20, 30,'CODIGO : ');
	  doc.text(60, 30, consulta.codigo);
	  doc.text(20, 50,'DESCRIPCION : ');
	  doc.text(90, 50, consulta.descripcion);
	  doc.text(20, 70,'PRECIO UNITARIO : ');
	  doc.text(100, 70, consulta.precioUnitario);
	  doc.text(20, 90,'PRECIO TOTAL : ');
	  doc.text(100, 90, consulta.precioTotal);
	  doc.text(20, 110,'STOCK MINIMO : ');
	  doc.text(90, 110, consulta.stockMinimo);
	  doc.text(20, 130,'STOCK ACTUAL : ');
	  doc.text(90, 130, consulta.stockActual);
	  doc.save('ArchivoDeConsulta.pdf');
	}
	
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
	
	self.toggleSeleccion = function(id) {
		self.seleccionados = self.seleccionados.has(id) ? self.seleccionados.remove(id) : self.seleccionados.add(id);  
	}
	
	
	
	self.cantSeleccionados = function() {
		return self.invertirSeleccion ? self.totalItems - self.seleccionados.size : self.seleccionados.size;
	}


});




