angular.module('myApp', ['ngRoute', 'ngResource', 'ui.bootstrap', 'uiGmapgoogle-maps']).
    config(['$routeProvider', 'uiGmapGoogleMapApiProvider', function ($routeProvider, uiGmapGoogleMapApiProvider) {
        $routeProvider
        	.when('/venue', {templateUrl: 'view/venue/list', controller: VenueListController})
        	.when('/venue/add', {templateUrl: 'view/venue/add', controller: VenueCreateController})
        	.when('/venue/:venueId', {templateUrl: 'view/venue/add', controller: VenueEditController})
        	.otherwise({ redirectTo: '/venue' });
        
        uiGmapGoogleMapApiProvider.configure({
        	v: '3.20'
        });
    }]).filter('courtType', function() {
    	var courtTypeMap = {
    		1: 'Indoor',
    		2: 'Covered',
    		3: 'Outdoor'
    	};
    	return function(input) {
    		var courtType = courtTypeMap[input];
    		return courtType || '';
    	};
    }).filter('floorType', function() {
    	var floorTypeMap = {
    		1: 'Hardwood',
    		2: 'Rubberized',
    		3: 'Cement'
    	};
    	return function(input) {
    		var floorType = floorTypeMap[input];
    		return floorType || '';
    	};
    }).filter('phPeso', ['currencyFilter', function(currencyFilter) {
    	return function(input) {
    		var result = currencyFilter(input, 'Php ', 2) + '/hr';
    		return result;
    	}
    }]).factory('Venue', ['$resource', function($resource) {
    	//TODO: Need to change the path to a more configurable way
    	var Venue = $resource('/basketball-app/venue/:venueId', { venueId: '@id'}, 
    			{
    				'search': { 'method': 'GET' },
    				'update': { 'method': 'PUT' }
    			})
    	return Venue;
    }]).run(['$rootScope', '$templateCache', function($rootScope, $templateCache) {
    	$rootScope.$on('$routeChangeStart', function(event, next, current) {
    		if(typeof(current) !== 'undefined') {
    			$templateCache.remove(current.templateUrl);
    		}
    	});
    }]).controller('VenueMapController', function($scope, $modalInstance, venueDetails) {
    	$scope.venue = venueDetails;
    	var myOptions = {
    		zoom: 16,
    		center: { lat: venueDetails.latitude, lng: venueDetails.longitude }
    	}
    	console.log(myOptions);
    	console.log(document.getElementById("map-canvas"));
    	var map = new google.maps.Map(document.getElementById("map-canvas"), myOptions)
    });


function VenueCreateController($scope, $location, Venue) {
	$scope.venue = new Venue();

	// Set Default Values
	$scope.venue.longitude = 15000;
	$scope.venue.latitude = 15000;
	$scope.venue.courtType = 1;
	$scope.venue.flooringType = 1;

	$scope.save = function() {
		$scope.venue.$save(function() {
			$location.path('/venue');
		});
	}
}

function VenueListController($scope, $modal, Venue) {
	$scope.searchCondition = "";
	$scope.city = "";
	$scope.province = "";
	$scope.pageNo = 1;
	var courtTypes = [ 'indoor', 'covered', 'outdoor' ];
	var floorTypes = [ 'hardWood', 'rubberized', 'cement' ];
	var setDefaults = function(obj, types) {
		var length = types.length;
		for (var i = 0; i < length; i++) {
			obj[types[i]] = true;
		}
	}
	setDefaults($scope.courtType = {}, courtTypes);
	setDefaults($scope.floorType = {}, floorTypes);

	var getTypeArr = function(obj, types) {
		var courtTypeArr = [];
		var length = types.length;
		for (var i = 0; i < length; i++) {
			var fieldName = types[i];
			if (obj[fieldName])
				courtTypeArr.push(i + 1);
		}
		return courtTypeArr.join(",");
	}

	var search = function(pageNo) {
		pageNo = pageNo || 1;
		Venue.search({
			pageNo : (pageNo - 1),
			searchCondition : $scope.searchCondition,
			province : $scope.province,
			city : $scope.city,
			courtType : getTypeArr($scope.courtType, courtTypes),
			floorType : getTypeArr($scope.floorType, floorTypes)
		}, function(response) {
			console.log(response.content);
			$scope.venues = response.content;
			$scope.totalPages = response.totalPages;
			$scope.totalElements = response.totalElements;
		});
	}

	search($scope.pageNo);

	$scope.searchAction = function(pageNo) {
		$scope.pageNo = pageNo;
		search($scope.pageNo);
	}

	$scope.showMap = function(inx) {
		console.log($scope.venues[inx]);
		var modalInstance = $modal.open({
			animation : true,
			templateUrl : 'venueMap.html',
			controller : 'VenueMapController',
			resolve : {
				venueDetails : function() {
					return $scope.venues[inx];
				}
			}
		});
	}
}

function VenueEditController($scope, $routeParams, $location, Venue) {
	$scope.venue = Venue.get({
		venueId : $routeParams.venueId
	});

	$scope.save = function() {
		$scope.venue.$update({
			venueId : $scope.venue.venueId
		}, function() {
			$location.path('/venue');
		});
	}
}