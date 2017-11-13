miGire.factory('UsuarioResource', function($resource) {
	return $resource('usuarios');
});


miGire.factory('EmailResource', function($resource) {
	return $resource('listaDeEmail');
});

miGire.directive("compareTo", function() {
    return {
      require: "ngModel",
      scope: {
        confirmPassword: "=compareTo"
      },
      link: function(scope, element, attributes, modelVal) {

        modelVal.$validators.compareTo = function(val) {
        		 return val == scope.confirmPassword; 
        };

        scope.$watch("confirmPassword", function() {
          modelVal.$validate();
        });
      }
    };
});


miGire.config(function($routeProvider) {
	$routeProvider.when('/abmUsuarios/lista', {
		templateUrl : 'abmUsuarios/listaUsuario.html',
		controller : 'UsuariosListaCtrl as ctrl'
	}).when('/abmUsuarios/edicion/:id', {
		templateUrl : 'abmUsuarios/altaUsuarios.html',
		controller : 'UsuarioAltaFormCtrl as ctrl',
		resolve : {
			ingreso : function(UsuarioResource, $route) {
				var id = $route.current.params['id'];
				return UsuarioResource.get({id : id}).$promise;
			},
			email : function(EmailResource){
				return new EmailResource();
				},

		}
	}).when('/abmUsuarios/alta', {
		templateUrl : 'abmUsuarios/altaUsuarios.html',
		controller : 'UsuarioAltaFormCtrl as ctrl',
		resolve : {
			ingreso : function(UsuarioResource) {	
				return new UsuarioResource(); },
			
			email : function(EmailResource){
				//return new EmailResource();
				return EmailResource.query().$promise;
			},
		}
	});	
});

miGire.factory('UsuarioResource', function($resource) {
	return $resource('usuarios/:id', {id: '@id', completo: true}, {
		'update' : {method : 'PUT'},
		'deleteList' : {method : 'DELETE'}
	});
})

miGire.controller('UsuariosListaCtrl', function(UsuarioResource, msgDialog, $log, $http, $q){
	var self = this;
	self.ingreso = UsuarioResource.query();

	
	self.reloadPage = true;
	
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

	
	self.remove = function(ingreso) {
		
		if(ingreso.active == 1){
			ingreso.active =2;
			var a = "INACTIVAR USUARIO";
			var b = "¿Esta seguro que desea inactivar el Usuario?";
		}
		else{
			ingreso.active =1;
			var a = "ACTIVAR USUARIO";
			var b = "¿Esta seguro que desea activar el Usuario?";
		}
	
		
		msgDialog.showMessage({		
			header: a, 
			message: b,
			buttons:[
			{label:"Si", action: function() {
				
				var REST_SERVICE_URI = 'http://localhost:8080/back/usuarios/modificaEstado/';		
				var deferred = $q.defer();
				
						$http.put(REST_SERVICE_URI+ingreso.id, ingreso)
			            		.then(
			            				function (response) {
			            					deferred.resolve(response.data);
			            					self.reloadPage = true;
			            				},
			            				function(errResponse){
			            					console.error('Error no se pudo cambiar el estado');
			            					deferred.reject(errResponse);
			            				}
			            		)
			
				//UsuarioResource.remove({id: ingreso.id}, function() {
				//	self.reloadPage = true;
			//	}, function(error) {
			//		msgDialog.showMessage({header: "Error", message: error.statusText});
			//	});
			}},
			{label:"No", action: function() {self.reloadPage = true;}}]
		});
	}
	
	
});


miGire.controller('UsuarioAltaFormCtrl', function($scope, ingreso, email, msgDialog, $location) {
	var self = this;
	self.ingreso = ingreso;
	self.email = email;
	
	self.tipoEstado = [{"active":1 , "descripcion":"ACTIVO"},{"active":2 , "descripcion":"INACTIVO"}];
		
	
	self.aceptar = function() {
		if(self.ingreso.id) {	
			
			self.ingreso.$update(function(response) {
				$location.path('abmUsuarios/lista');
				
			}, function(error) {
				if(error.data && error.data.valor)
					msgDialog.showMessage({header: "Fallo la Modificaci&oacuten Del Usuario", message: error.data.valor});
				else
					msgDialog.showMessage({header: "Ha ocurrido un error", message: error.statusText});
			})
		} else {

			self.ingreso.active = 1;			
			var algos = beto(email, ingreso);
			
			if(algos == true)
				{
				msgDialog.showMessage({
					header: "Aviso", 
					message: "El email ingresado ya fue dado de alta, ingrese otro por favor", 
					buttons:[
					{label:"Entendido", action: function() {
					return;
					}}]
				});
				}
			else
				{
				self.ingreso.$save(function(response) {
					$location.path('abmUsuarios/lista');
				}, function(error) {
					if(error.data && error.data.valor)
						msgDialog.showMessage({header: "Se dio de alta el usuario", message: error.data.valor});
					else 
					msgDialog.showMessage({header: "Ha ocurrido un error", message: error.statusText});
				});
				}
			
			
			
			
			
		}
	};
	
	
	function beto(email, ingreso) {
		
		for(var i = 0; i < self.email.length; i++){
			
			if(self.email[i].email == self.ingreso.email){				
				return true;				
			}		
		}
		
		return false;
		
	}

	
});
