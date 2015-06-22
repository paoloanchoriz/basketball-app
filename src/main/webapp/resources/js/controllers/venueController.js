/**
 * 
 */
var venueController = angular.module('venueController', ['ui.bootstrap']);

	venueController.controller('VenueFormController',
		[ '$scope', '$routeParams', '$location', '$modal', 'Venue', 'Constants', 
		  function($scope, $routeParams, $location, $modal,
					Venue, Constants) {
				if ($routeParams.venueId) {
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
				} else {
					$scope.venue = new Venue();
					// Set Default Values
					$scope.venue.courtType = 1;
					$scope.venue.flooringType = 1;
					
					$scope.save = function() {
						$scope.venue.$save(function() {
							$location.path('/venue');
						});
					}
				}

				$scope.provinces = Constants.provinceList;
				$scope.provinceOptions = {
					displayText : 'Province'
				}

				$scope.locationsMap = Constants.locationsMap;
				$scope.cityOptions = {
					displayText : 'City/Municipality'
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
				};
			}]);

	venueController.controller('VenueListController',
			[ '$scope', '$modal', 'Venue', 'Constants', 
			  function($scope, $modal, Venue, Constants) {
				$scope.searchCondition = "";
				$scope.city = "";
				$scope.province = "";
				$scope.pageNo = 1;
				
				$scope.provinces = Constants.provinceList;
				$scope.provinceOptions = {
					displayText: 'Province'
				}
				
				$scope.locationsMap = Constants.locationsMap;
				$scope.cityOptions = {
					displayText: 'City/Municipality'
				}
				
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
			}]);

