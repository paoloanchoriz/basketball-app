/**
 * 
 */
angular.module('venueService', ['ngResource'])
	.factory('Venue', ['$resource', function($resource) {
    	//TODO: Need to change the path to a more configurable way
    	var Venue = $resource('/basketball-app/venue/:venueId', { venueId: '@id'}, 
    			{
    				'search': { 'method': 'GET' },
    				'update': { 'method': 'PUT' }
    			})
    	return Venue;
    }]);