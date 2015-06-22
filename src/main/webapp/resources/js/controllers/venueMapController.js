/**
 * 
 */
angular.module('venueMap',
		[
			'uiGmapgoogle-maps', 
			'ui.filters', 
	]).controller('VenueMapController', 
		['$scope', '$modalInstance', 'venueDetails', 
		 	function($scope, $modalInstance, venueDetails) {
		    	$scope.venue = venueDetails;
		    	$scope.map = {
		    		zoom: 16,
		    		center: { latitude: venueDetails.latitude, longitude: venueDetails.longitude }
		    	};
		    	$scope.options = { disableDoubleClickZoom: true };
		    	
		    	$scope.render = true;
		    	
		    	$scope.marker = {
					id: 0,
					coords: {
						latitude: venueDetails.latitude,
						longitude: venueDetails.longitude
					},
					render: true
				}
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
	 								$scope.$apply(setCenter(location.lat(), location.lng()));
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