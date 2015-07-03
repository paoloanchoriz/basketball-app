angular.module('myApp', 
		[
		 'ngRoute', 
		 'AxelSoft', 
		 'basketballAppFilters',
		 'venueMap',
		 'staticMap',
		 'venueService',
		 'ui.bootstrap',
		 'venueController',
		 'gameScheduleController'
		 ]
	).constant('Constants',{
		locationsMap: '',
		provinceList: ''
	}).config(['$routeProvider', 'uiGmapGoogleMapApiProvider', function ($routeProvider, uiGmapGoogleMapApiProvider) {
        $routeProvider
        	.when('/venue', {templateUrl: 'view/venue/list', controller: 'VenueListController'})
        	.when('/venue/add', {templateUrl: 'view/venue/add', controller: 'VenueFormController'})
        	.when('/venue/:venueId', {templateUrl: 'view/venue/add', controller: 'VenueFormController'})
        	
        	.when('/gameSchedule/add', {templateUrl: 'view/gameSchedule/add', controller: 'GameScheduleFormController'})
        	
        	.otherwise({ redirectTo: '/venue' });
        
        uiGmapGoogleMapApiProvider.configure({
        	v: '3.20'
        });
    }]).run(['$rootScope', '$templateCache', function($rootScope, $templateCache) {
    	$rootScope.$on('$routeChangeStart', function(event, next, current) {
    		if(typeof(current) !== 'undefined') {
    			$templateCache.remove(current.templateUrl);
    		}
    	});
    }]).run(['$http', 'Constants',function($http, Constants) {
    	//TODO: Need to change the path to a more configurable way
    	var getLocationsMap = $http.get('/basketball-app/locationsMap').then(function(data) {
    		return data;
    	});
    	
    	getLocationsMap.then(function(result) {
    		var locationsMap = result.data;
    		var provinceList = [];
    		for(var province in locationsMap) {
    			provinceList.push(province);
    		}
    		Constants.locationsMap = locationsMap;
    		Constants.provinceList = provinceList;
    	});
    }]);


