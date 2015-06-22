/**
 * 
 */
angular.module('basketballAppFilters',[])
	.filter('courtType', function() {
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
    }]);