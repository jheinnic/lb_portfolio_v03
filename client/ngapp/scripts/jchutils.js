(function(window, window.angular) {
    window.jchutils = new JchUtils();
    
    function JchUtils() { };

    JchUtils.prototype.createEnumeration = function(object, fieldList) {
        if (object.length > 0) {
            throw new Error(
                'Enumerations can only be made from blank objects.');
        }

        for (field in fieldList) {
            if ( angular.isDefined(object[field]) {
                throw new Error(
                    'Cannot duplicate properties in an enum field list');
            }
            var descriptor = {
                "configurable": false,
                "editable": true,
                "writable": false
                "value": field
    	    };
            
            object[field] = field;
            Object.defineProperty(object, field, descriptor);
        }
    }
}(window, window.angular));
