miGire.factory('OrdenTrabajoResource', function($resource) {
	return $resource('OrdenTrabajo');
});

miGire.factory('CantidadStockResource', function($resource) {
	return $resource('cantidadStock');
});


miGire.config(function($routeProvider) {
	$routeProvider.when('/ordenTrabajo/alta', {
		templateUrl : 'ordenTrabajo/ordenTrabajo.html',
		controller : 'OrdenTrabajoFormCtrl as ctrl',
		resolve : {
			orden : function(OrdenTrabajoResource) {	
				return new OrdenTrabajoResource();
				},
				empresas : function(CantidadStockResource){
					return CantidadStockResource.query().$promise;
				},
		}
	});
});



miGire.controller('OrdenTrabajoFormCtrl', function($scope, orden, msgDialog, $location, empresas) {
	
	var self = this;
	self.orden = orden;
	self.empresas = empresas;
	$scope.carrito = [];
	
	$scope.agregar = function (p) {
        var itemActual;

        for (var i = 0; i < $scope.carrito.length; i++) {
            if ($scope.carrito[i].empresas.id == p.id) {
                itemActual = $scope.carrito[i];
            }
        }

        if (!itemActual) {
            $scope.carrito.push({
            	empresas: p,
                Cantidad: 1
            });
        } else {
            itemActual.Cantidad++;
        }
    }

	
/*	self.remove = function(ingreso) {
		msgDialog.showMessage({
			header: "Aviso", 
			message: "No puede realizar la orden de trabajo porque no tiene stock suficiente", 
			buttons:[
			{label:"Si", action: function() {
				IngresoStockResource.remove({id: ingreso.id}, function() {
					self.reloadPage = true;
				}, function(error) {
					msgDialog.showMessage({header: "Error", message: error.statusText});
				});
			}},
			{label:"No", action: function() {}}]
		});
	}
	*/
	self.aceptar = function() {
			
			self.empresas;
			self.item;
		
			self.orden.$save(function(response) {
				$location.path('/ingresoStock/lista');
			}, function(error) {
				if(error.data && error.data.valor)
					msgDialog.showMessage({header: "Alta de ingreso de stock", message: error.data.valor});
				else 
				msgDialog.showMessage({header: "Ha ocurrido un error", message: error.statusText});
			});
	}
	
	
	
	self.remove = function(idElemento)
	{
		var index = $scope.carrito.indexOf(idElemento);
		$scope.carrito.splice(index,1);
	}
	
	self.pdf = function()
	{
	  var doc = new jsPDF();
	  
	  doc.text(20,20, orden.sector);
	  doc.text(20,30, orden.responsable);
	  doc.text(20,40, orden.equipo);
	 // doc.text(20,50, orden.fecha);
	  doc.text(20,60, orden.orden);
	  doc.text(20,70, orden.tarea);
	  doc.text(20,80, orden.tiempo);
	  doc.text(20,90, orden.herramienta);
	  //doc.text(20,100,'TESTdasdas Mensaje!!');
	  //doc.addPage();
	  //doc.text(20,20,'TEST Mensaje dos');
	  doc.save('text.pdf');
	}
	
	
});