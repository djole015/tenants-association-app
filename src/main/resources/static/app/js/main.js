var associationApp = angular.module("associationApp",["ngRoute"]);

associationApp.controller("announcementsCtrl", function($scope, $http, $location){
	
	$scope.announcements = [];
	$scope.flats = [];
	
	$scope.newAnnouncement = {};
	$scope.newAnnouncement.title = "";
	$scope.newAnnouncement.type = "";
	$scope.newAnnouncement.percentageNeeded = "";
	$scope.newAnnouncement.description = "";

	$scope.newAnnouncement.flatId = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	var announcementsUrl = "/api/announcements";
	var flatsUrl = "/api/flats";
	
	$scope.searchParams = {};
	$scope.searchParams.title = "";
	$scope.searchParams.type = "";
	$scope.searchParams.flatId = "";
	
	var getAnnouncements = function(){
		
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
		
		$http.get(announcementsUrl, config).then(
			function success(res){
				$scope.announcements = res.data;
				$scope.totalPages = res.headers("totalPages");
				getFlats();
			},
			function error(){
				alert("Fetching announcements failed.");
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
	
	getAnnouncements();
	
	var getAnnouncement = function(id){
		$http.get(announcementsUrl + "/" + id).then(
				function success(res){
					$scope.announcement = res.data;
				},
				function error(){
					alert("Fetching announcement failed.");
				}
			);
	}
	
	$scope.doAdd = function(){
		
		$http.post(announcementsUrl, $scope.newAnnouncement).then(
			function success(){
				getAnnouncements();
				
				$scope.newAnnouncement.title = "";
				$scope.newAnnouncement.type = "";
				$scope.newAnnouncement.percentageNeeded = "";
				$scope.newAnnouncement.description = "";

				$scope.newAnnouncement.flatId = "";
				
			},
			function error(){
				alert("Saving announcement failed!");
			}
		);
	}
	
	$scope.doDelete = function(id){
		$http.delete(announcementsUrl + "/" + id).then(
			function success(){
				getAnnouncements();
			},
			function error(){
				alert("Removing announcement failed.");
			}
		);
	}
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getAnnouncements();
		
		$scope.searchParams.title = "";
		$scope.searchParams.type = "";
		
		$scope.searchParams.flatId = "";
	}
	
	$scope.changePage = function(direction){
		$scope.pageNum = $scope.pageNum + direction;
		getAnnouncements();
	}
	
	$scope.goToEdit = function(id){
		$location.path("/edit/" + id);
	}
	
	$scope.goToVote = function(id){
		$location.path("/vote/" + id);
	}
	
});

associationApp.controller("editAnnouncementCtrl", function($scope, $http, $routeParams, $location){
	
	var announcementUrl = "/api/announcements/" + $routeParams.id;
	var flatsUrl = "/api/flats";

	$scope.flats = [];
	
	$scope.announcement = {};
	$scope.announcement.title = "";
	$scope.announcement.type = "";
	$scope.announcement.percentageNeeded = "";
	$scope.announcement.description = "";

	$scope.announcement.flatId = "";
	
	$scope.vote = {};
	$scope.vote.accept = "";
	$scope.vote.announcementId = "";
	
	
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
	
	var getAnnouncement = function(){
		$http.get(announcementUrl).then(
			function success(res){
				$scope.announcement = res.data;
			},
			function error(){
				alert("Fetching announcement failed.");
			}
		);
	}

	getFlats();
	getAnnouncement();
	
	$scope.doEdit = function(){
		$http.put(announcementUrl, $scope.announcement).then(
			function success(){
				$location.path("/");
			},
			function error(){
				alert("Saving announcement failed.");
			}
		);
	}
	
	$scope.voteFor = function(id){
		$scope.vote.accept = "yes";
		$scope.vote.announcementId = id;
		
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
		$scope.vote.announcementId = id;
		
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
			templateUrl : '/app/html/announcements.html'
		})
		.when('/edit/:id', {
			templateUrl : '/app/html/edit-announcement.html'
		})
		.when('/vote/:id', {
			templateUrl : '/app/html/vote.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);
