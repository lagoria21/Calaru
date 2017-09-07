miGire.factory('SolucionResource', function($resource) {
	return $resource('soluciones');
});

miGire.factory('ServicioResource', function($resource) {
	return $resource('servicios');
});

miGire.factory('TipoTramiteResource', function($resource) {
	return $resource('tiposTramites');
});

miGire.factory('MotivoResource', function($resource) {
	return $resource('motivo');
});

//miGire.factory('EntidadResource', function($resource){
//	return $resource('entidad');
//});

miGire.factory('ArchivoResource', function($resource){
	return $resource('archivo/getListArchivos');
})

miGire.factory('EmpresaResource', function($resource) {
	return $resource('empresa');
});

miGire.factory('TodaEmpresaResource', function($resource) {
	return $resource('todaEmpresa');
});

miGire.factory('EmpresaEntidadResource', function($resource) {
	return $resource('empresaEntidad');
});
miGire.factory('BancoResource', function($resource){
	return $resource('bancos');
});

miGire.factory('EmpresasByServicioResource', function($resource) {
	return $resource('empresasByServicio', {id:'@id'});
});

//miGire.factory('EntidadesByServicioResource', function($resource) {
//	return $resource('entidadesByServicio', {id:'@id'});
//});



