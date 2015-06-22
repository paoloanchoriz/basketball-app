/**
 * 
 */
angular.module('staticMap', [])
	.directive('staticMap', function() {
    	var zoom = 'zoom=13';
    	var maptype= 'maptype=roadmap';
    	var url = 'https://maps.googleapis.com/maps/api/staticmap';
    	var markers = 'markers=color:red|';
    	var size = 'size=600x400';

    	return {
    		restrict: 'E',
    		replace: true,
    		template: '<img class="img-responsive clickable" alt="" ng-click="openMap()" />',
    		scope: {
    			latitude: '@',
    			longitude: '@',
    			venueName: '@',
    			openMap: '&'
    		},
    		link: function(scope, element, attr, controller) {
    			var mapMarker = markers + scope.latitude + ',' + scope.longitude;
    			var imgUrl = url + '?' + mapMarker + '&' + zoom + '&' + maptype + '&' + size;
    			
    			attr.$set('src', imgUrl);
    			attr.$set('alt', scope.venueName);
    			
    		}
    	}
    });