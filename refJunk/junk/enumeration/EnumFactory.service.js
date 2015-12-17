(function(window) {
    window.jchutils = new JchUtils();

    function JchUtils() { }

    JchUtils.prototype.createEnumeration = function(object) {
        if (object.length > 0) {
            throw new Error(
                'Enumerations can only be made from blank objects.');
        }

        var field;
        for (field in object.getOwnPropertyNames()) {
            // Redundant check, yes, but it silences a jshint warning.
            if (object.hasOwnProperty(field)) {
                var descriptor = {
                    "configurable": false,
                    "editable": true,
                    "writable": false,
                    "value": field
                };

                // object[field] = field;
                delete object[field];
                Object.defineProperty(object, field, descriptor);
            }
        }

        object.freeze();
    }
}(window));
