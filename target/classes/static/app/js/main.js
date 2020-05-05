var evidencijaApp = angular.module("evidencijaApp",["ngRoute"]);

evidencijaApp.controller("homeCtrl", function($scope){
	$scope.message="Zavrsni ispit";
});

evidencijaApp.controller("zadaciCtrl", function($scope, $http, $location){
	
	$scope.zadaci = [];
	$scope.sprintovi = [];
	$scope.stanja = [];

	$scope.newZadatak = {};
	$scope.newZadatak.ime = "";

	$scope.newZadatak.sprintId = "";
	$scope.newZadatak.stanjeId = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	var zadaciUrl = "/api/zadaci";
	var sprintoviUrl = "/api/sprintovi";
	var stanjaUrl = "/api/stanja";
	
	$scope.searchParams = {};
	$scope.searchParams.ime = "";
	$scope.searchParams.sprintId = "";
	
	var getZadaci = function(){
		
		var config = { params: {} };
		
		if($scope.searchParams.ime != ""){
		config.params.ime = $scope.searchParams.ime;
		}
	
		if($scope.searchParams.sprintId != ""){
		config.params.sprintId = $scope.searchParams.sprintId;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		var promise = $http.get(zadaciUrl, config);
		promise.then(
			function success(res){
				$scope.zadaci = res.data;
				$scope.totalPages = res.headers("totalPages");
			},
			function error(){
				alert("Neupešno dobavljanje zadataka.");
			}
		);
	}
	
	getZadaci();
	
	
	var getSprintovi = function(){
		$http.get(sprintoviUrl).then(
			function success(res){
				$scope.sprintovi = res.data;
			},
			function error(){
				alert("Neuspešno dobavljanje sprintova.");
			}
		);
	}
	
	getSprintovi();
	
	var getStanja = function(){
		$http.get(stanjaUrl).then(
			function success(res){
				$scope.stanja = res.data;
			},
			function error(){
				alert("Neuspešno dobavljanje stanja.");
			}
		);
	}
	
	getStanja();
	
	$scope.doAdd = function(){
		
		$http.post(zadaciUrl, $scope.newZadatak).then(
			function success(){
				getZadaci();
				
				$scope.newZadatak.ime = "";

				$scope.newZadatak.sprintId = "";
				$scope.newZadatak.stanjeId = "";
			},
			function error(){
				alert("Neuspešno čuvanje zadatka!");
			}
		);
	}
	
	$scope.doDelete = function(id){
		$http.delete(zadaciUrl + "/" + id).then(
			function success(){
				getZadaci();
			},
			function error(){
				alert("Neuspešno brisanje zadatka.");
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/edit/" + id);
	}
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getZadaci();
		
		$scope.searchParams.ime = "";
		$scope.searchParams.sprintId = "";
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getZadaci();
	}
	
});

evidencijaApp.controller("editZadatakCtrl", function($scope, $http, $routeParams, $location){
	
	var zadatakUrl = "/api/zadaci/" + $routeParams.id;
	var sprintoviUrl = "/api/sprintovi";
	var stanjaUrl = "/api/stanja";

	$scope.sprintovi = [];
	$scope.stanja = [];
	
	$scope.zadatak = {};
	$scope.zadatak.ime = "";

	$scope.zadatak.stanjeId = "";
	$scope.zadatak.sprintId = "";
	
	var getStanja = function(){
		$http.get(stanjaUrl).then(
			function success(res){
				$scope.stanja = res.data;
				getSprintovi();
			},
			function error(){
				alert("Neuspešno dobavljanje stanja.");
			}
		);
	}
	
	var getSprintovi = function(){
		$http.get(sprintoviUrl).then(
			function success(res){
				$scope.sprintovi = res.data;
				getZadatak();
			},
			function error(){
				alert("Neuspešno dobavljanje sprintova.");
			}
		);
	}
	
	var getZadatak = function(){
		$http.get(zadatakUrl).then(
			function success(res){
				$scope.zadatak = res.data;
			},
			function error(){
				alert("Neuspešno dobavljanje zadatka.");
			}
		);
	}
	
	getStanja();
	
	$scope.doEdit = function(){
		$http.put(zadatakUrl, $scope.zadatak).then(
			function success(){
				$location.path("/");
			},
			function error(){
				alert("Neuspešno čuvanje zadatka.");
			}
		);
	}
});


evidencijaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/zadaci.html'
		})
		.when('/edit/:id', {
			templateUrl : '/app/html/edit-zadatak.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
