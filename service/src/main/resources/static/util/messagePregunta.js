var msgDlgModule = angular.module('gire.messagePregunta', ['ui.bootstrap']);
msgDlgModule.factory("msgPregunta", function($uibModal, $log){
	return {
		showMessage: function(dlgModel) {
			var modalInstance = $uibModal.open({
					templateUrl: 'util/messagePregunta.html',
					controller: 'ModalInstanceCtrl',
					resolve: {model: dlgModel},
					size: dlgModel.size
			});
			modalInstance.result.then(function(action) {
				$log.log("pre call to action:" + action);
				action();
			});
		}
	};
});
msgDlgModule.controller('ModalInstanceCtrl', function($scope, $uibModalInstance, model) {
	$scope.buttons = [];
	if(model.buttons) {
		model.buttons.forEach(function(b) {
			$scope.buttons.push({
				label : b.label,
				action : function() {
					$uibModalInstance.close(b.action);
				}
			})
		});
	} else {
		$scope.buttons.push({
			label: 'Aceptar',
			action: function() {$uibModalInstance.dismiss()}
		});
	}
	$scope.header = model.header;
	$scope.message = model.message;
});

