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
    }]).directive('staticMap', function() {
    	var zoom = 'zoom=13';
    	var maptype= 'maptype=roadmap';
    	var url = 'https://maps.googleapis.com/maps/api/staticmap';
    	var markers = 'markers=color:red|';
    	var size = 'size=600x400';
    	
    	 
    	
    	return {
    		restrict: 'E',
    		replace: true,
    		template: '<img class="img-responsive clickable" alt="" />',
    		scope: {
    			latitude: '@',
    			longitude: '@',
    			venueName: '@',
    			onClick: '&onClick'
    		},
    		link: function(scope, element, attr, controller) {
    			var mapMarker = markers + scope.latitude + ',' + scope.longitude;
    			var imgUrl = url + '?' + mapMarker + '&' + zoom + '&' + maptype + '&' + size;
    			
    			attr.$set('src', imgUrl);
    			attr.$set('alt', scope.venueName);
    			
    		}
    	}
    }).factory('Venue', ['$resource', function($resource) {
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
    }]).controller('VenueMapController', 
    		['$scope', '$modalInstance', 'venueDetails', 
    		 	function($scope, $modalInstance, venueDetails) {
			    	$scope.venue = venueDetails;
			    	$scope.map = {
			    		zoom: 16,
			    		center: { latitude: venueDetails.latitude, longitude: venueDetails.longitude }
			    	};
			    	$scope.options = { disableDoubleClickZoom: true };
			    	
			    	$scope.render = true;
			    }
    ]).controller('VenuePickerController', 
    		['$scope', '$modalInstance', 'venueDetails', 'uiGmapGoogleMapApi',
    		 	function($scope, $modalInstance, venueDetails, uiGmapGoogleMapApi) {
    			
    				$scope.returnCoordinates = function() {
    					$modalInstance.close($scope.coordinates);
    				};
    				
    				var setMarker = function(latitude, longitude) {
    					$scope.marker = {
    						id: 0,
    						coords: {
    							latitude: latitude,
    							longitude: longitude
    						},
    						render: true
    					}
    					$scope.coordinates = {};
    					$scope.coordinates.latitude = latitude;
    					$scope.coordinates.longitude = longitude;
    				};
    				
    				var setCenter = function(lat, lng) {
    					$scope.map = {
    						zoom: 15,
    						center: { latitude: lat, longitude: lng },
    						events: {
    							dblclick: function(mapModel, eventName, originalEventArgs) {
    								var coordinates = originalEventArgs[0].latLng;
    								
    								$scope.$apply(function() {
    									setMarker(coordinates.lat(), coordinates.lng());
    								});
    							}
    						}
    					};
    					$scope.options = { disableDoubleClickZoom: true };
    					$scope.render = true;
    				};
    				
    				var setCurrentLocation = function() {
    					navigator.geolocation.getCurrentPosition(function(position) {
    						$scope.$apply(
    								setCenter(position.coords.latitude, 
    										position.coords.longitude));
    					});
    				};
    				
    				if(venueDetails.longitude && venueDetails.latitude) {
						setCenter(venueDetails.latitude, venueDetails.longitude);
						setMarker(venueDetails.latitude, venueDetails.longitude);
					} else if(venueDetails.city + venueDetails.province){
						var addressArr = [];
						var city = venueDetails.city;
						var province = venueDetails.province;
						if(city) addressArr.push(city);
						if(province) addressArr.push(province);
						
						if(addressArr.length) {
							addressArr.push('Philippines');
							var address = addressArr.join(',');
							uiGmapGoogleMapApi.then(function(maps) {
								var geocoder = new maps.Geocoder();
	    						geocoder.geocode({
	    							'address': address
	    						}, function(results, status) {
	    							if(status === google.maps.GeocoderStatus.OK) {
	    								var location = results[0].geometry.location;
	    								$scope.$apply(
	    										setCenter(location.lat(), 
	    												location.lng()));
	    							} else {
	    								setCurrentLocation();
	    							}
	    						});
							});
						}
					} else {
						setCurrentLocation();
					}
    			}
    ]);

function VenueEditController($scope, $routeParams, $location, $modal, Venue) {
	$scope.venue = Venue.get({
		venueId : $routeParams.venueId
	});

	$scope.save = function() {
		$scope.venue.$update({
			venueId : $scope.venue.venueId
		}, function() {
			$location.path('/venue');
		});
	};
	
	$scope.resetCoordinates = function() {
		$scope.venue.latitude = '';
		$scope.venue.longitude = '';
		$scope.openMapModal();
	};
	
	$scope.openMapModal = function() {
		var modalInstance = $modal.open({
			animation : true,
			templateUrl : 'venueMap.html',
			controller : 'VenuePickerController',
			resolve : {
				venueDetails : function() {
					return $scope.venue;
				}
			}
		});
		
		modalInstance.result.then(function(coordinates) {
			$scope.venue.latitude = coordinates.latitude;
			$scope.venue.longitude = coordinates.longitude;
		});
	};
}

function VenueCreateController($scope, $location, $modal, Venue) {
	$scope.venue = new Venue();

	// Set Default Values
	$scope.venue.courtType = 1;
	$scope.venue.flooringType = 1;
	
	$scope.save = function() {
		$scope.venue.$save(function() {
			$location.path('/venue');
		});
	}
	
	$scope.resetCoordinates = function() {
		$scope.venue.latitude = '';
		$scope.venue.longitude = '';
		$scope.openMapModal();
	};
	
	$scope.openMapModal = function() {
		var modalInstance = $modal.open({
			animation : true,
			templateUrl : 'venueMap.html',
			controller : 'VenuePickerController',
			resolve : {
				venueDetails : function() {
					return $scope.venue;
				}
			}
		});
		
		modalInstance.result.then(function(coordinates) {
			$scope.venue.latitude = coordinates.latitude;
			$scope.venue.longitude = coordinates.longitude;
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

