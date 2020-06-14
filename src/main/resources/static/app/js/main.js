var associationApp = angular.module("associationApp",["ngRoute"]);

associationApp.controller("messagesCtrl", function($scope, $http, $location){
	
	$scope.messages = [];
	$scope.flats = [];
	
	$scope.newMessage = {};
	$scope.newMessage.title = "";
	$scope.newMessage.type = "";
	$scope.newMessage.percentageNeeded = "";
	$scope.newMessage.description = "";

	$scope.newMessage.flatId = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	var messagesUrl = "/api/messages";
	var flatsUrl = "/api/flats";
	
	$scope.searchParams = {};
	$scope.searchParams.title = "";
	$scope.searchParams.type = "";
	$scope.searchParams.flatId = "";
	
	var getMessages = function(){
		
		var config = { params: {} };
		
		if($scope.searchParams.title != ""){
		config.params.title = $scope.searchParams.title;
		}
		
		if($scope.searchParams.type != ""){
		config.params.type = $scope.searchParams.type;
		}
	
		if($scope.searchParams.flatId != ""){
		config.params.flatId = $scope.searchParams.flatId;
		}
		
		config.params.pageNum = $scope.pageNum;
		
		$http.get(messagesUrl, config).then(
			function success(res){
				$scope.messages = res.data;
				$scope.totalPages = res.headers("totalPages");
				getFlats();
			},
			function error(){
				alert("Fetching messages failed.");
			}
		);
	}

	var getFlats = function(){
		$http.get(flatsUrl).then(
			function success(res){
				$scope.flats = res.data;
			},
			function error(){
				alert("Fetching flats failed.");
			}
		);
	}
	
	getMessages();
	
	var getMessage = function(id){
		$http.get(messagesUrl + "/" + id).then(
				function success(res){
					$scope.message = res.data;
				},
				function error(){
					alert("Fetching message failed.");
				}
			);
	}
	
	$scope.doAdd = function(){
		
		$http.post(messagesUrl, $scope.newMessage).then(
			function success(){
				getMessages();
				
				$scope.newMessage.title = "";
				$scope.newMessage.type = "";
				$scope.newMessage.percentageNeeded = "";
				$scope.newMessage.description = "";

				$scope.newMessage.flatId = "";
				
			},
			function error(){
				alert("Saving message failed!");
			}
		);
	}
	
	$scope.doDelete = function(id){
		$http.delete(messagesUrl + "/" + id).then(
			function success(){
				getMessages();
			},
			function error(){
				alert("Removing message failed.");
			}
		);
	}
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getMessages();
		
		$scope.searchParams.title = "";
		$scope.searchParams.type = "";
		
		$scope.searchParams.flatId = "";
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getMessages();
	}
	
	$scope.goToEdit = function(id){
		$location.path("/edit/" + id);
	}
	
	$scope.goToVote = function(id){
		$location.path("/vote/" + id);
	}
	
});

associationApp.controller("editMessageCtrl", function($scope, $http, $routeParams, $location){
	
	var messageUrl = "/api/messages/" + $routeParams.id;
	var flatsUrl = "/api/flats";

	$scope.flats = [];
	
	$scope.message = {};
	$scope.message.title = "";
	$scope.message.type = "";
	$scope.message.percentageNeeded = "";
	$scope.message.description = "";

	$scope.message.flatId = "";
	
	$scope.vote = {};
	$scope.vote.accept = "";
	$scope.vote.messageId = "";
	
	
	var getFlats = function(){
		$http.get(flatsUrl).then(
			function success(res){
				$scope.flats = res.data;
			},
			function error(){
				alert("Fetching flats failed.");
			}
		);
	}
	
	var getMessage = function(){
		$http.get(messageUrl).then(
			function success(res){
				$scope.message = res.data;
			},
			function error(){
				alert("Fetching message failed.");
			}
		);
	}

	getFlats();
	getMessage();
	
	$scope.doEdit = function(){
		$http.put(messageUrl, $scope.message).then(
			function success(){
				$location.path("/");
			},
			function error(){
				alert("Saving message failed.");
			}
		);
	}
	
	$scope.voteFor = function(id){
		$scope.vote.accept = "yes";
		$scope.vote.messageId = id;
		
		$http.post("/api/votes/", $scope.vote).then(
				function success(){
					alert("Vote successfully saved");
					$location.path("/");
				},
				function error(){
					alert("Saving vote failed.");
				}
		);
	}
	
	$scope.voteAgainst = function(id){
		$scope.vote.accept = "no";
		$scope.vote.messageId = id;
		
		$http.post("/api/votes/", $scope.vote).then(
				function success(){
					alert("Vote successfully saved");
					$location.path("/");
				},
				function error(){
					alert("Saving vote failed.");
				}
		);
	}
});

associationApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/messages.html'
		})
		.when('/edit/:id', {
			templateUrl : '/app/html/edit-message.html'
		})
		.when('/vote/:id', {
			templateUrl : '/app/html/vote.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
