Array.prototype.unique = function () {
    // Accept or generate a unique string that is not a defined key in any object
    var i, j;
    var accountedFor = {};
    var uniqueString = '_~' + Math.random();
    for (i = 0; i < this.length; i++) {
        if (typeof this[i] == "object") {
            // Keep a collection of every intrinsic property we've seen thus far
            var props = Object.keys(this[i]);
            for( j = 0; j < props.length; j++ ) {
                accountedFor[props[j]] = true;
            }

            // Regenerate uniqueString until it is no longer found in our property collection.
            while (accountedFor[uniqueString] != undefined) {
                uniqueString = '_~' + Math.random();
            }
        }
    }

    var item;
    var result = [];
    accountedFor = {};
    for (i = 0; i < this.length; i++) {
        item = this[i];
        if (typeof item == "object") {
            if (!item[uniqueString]) {
                result.push(item);
                item[uniqueString] = true;
            }
        } else {
            var key = (typeof item) + item;
            if (!accountedFor[key]) {
                result.push(item);
                accountedFor[key] = true;
            }
        }
    }

    // Now remove the unique string from any objects we stuck it into
    for (i = 0; i < this.length; i++) {
        item = this[i];
        if (typeof item == "object") {
            delete item[uniqueString];
        }
    }
    return result;
}