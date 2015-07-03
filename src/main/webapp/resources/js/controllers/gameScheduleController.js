/**
 * 
 */

var gameScheduleController = angular.module('gameScheduleController', []);

	gameScheduleController.controller('GameScheduleFormController', 
			['$scope', '$routeParams', '$location', 'Constants', 'Venue',
			 function($scope, $routeParams, $location, Constants, Venue) {
				
				$scope.provinces = Constants.provinceList;
				$scope.provinceOptions = {
					displayText : 'Province'
				}

				$scope.locationsMap = Constants.locationsMap;
				$scope.cityOptions = {
					displayText : 'City/Municipality'
				}
				
				$scope.fetchVenues = function(venueName) {
					var venuePromise = Venue.search({
						pageNo : 0,
						searchCondition : venueName,
						province : $scope.province,
						city : $scope.city,
						courtType : "1,2,3",
						floorType : "1,2,3"
					});
					
					return venuePromise.$promise.then(function(response) {
						return response.content.map(function(venue) {
							var retValue = { venueName: venue.venueName, venueId: venue.venueId };
							return retValue;
						});
					});
				}
				
				$scope.onSelect = function($item) {
					$scope.game.venueId = $item.venueId;
					$scope.game.venueName = $item.venueName;
				}
				
				
				$scope.save = function() {
					console.log($scope);
				}
			}]);