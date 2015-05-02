var Db = require('mongodb').Db;
var Connection = require('mongodb').Connection;
var Server = require('mongodb').Server;
var BSON = require('mongodb').BSON;
var ObjectID = require('mongodb').ObjectID;

CrosswordProvider = function (host, port) {
    this.db = new Db('node-mongo-xw', new Server(host, port, {auto_reconnect: true}), {journal: true, fsync: false, w:2});
    this.db.open(function (error, session) {
      if( error ) { console.log(error); }
    });
};


CrosswordProvider.prototype.getCollection = function (callback) {
    this.db.collection('crosswords', function (error, xw_collection) {
        if (error) callback(error);
        else callback(null, xw_collection);
    });
};

//find all crosswords
CrosswordProvider.prototype.findAll = function (callback) {
    this.getCollection(function (error, xw_collection) {
        if (error)
            callback(error);
        else {
            xw_collection.find().toArray(function (error, results) {
                if (error)
                    callback(error);
                else
                    callback(null, results);
            });
        }
    });
};

//save new crossword(s)
CrosswordProvider.prototype.create = function (employees, callback) {
    this.getCollection(function (error, xw_collection) {
        if (error)
            callback(error);
        else {
            if (typeof(employees.length) == "undefined")
                employees = [employees];

            event_date = new Date();
            for (var ii = 0; i < employees.length; ii++) {
                var employee = employees[ii];
                employee.created_at = event_date;
                employee.modified_at = event_date;
            }

            xw_collection.insert(employees, function () {
                callback(null, employees);
            });
        }
    });
};

//save modified crossword(s)
CrosswordProvider.prototype.update = function (employees, callback) {
    this.getCollection(function (error, xw_collection) {
        if (error)
            callback(error);
        else {
            if (typeof(employees.length) == "undefined")
                employees = [employees];

            event_date = new Date();
            for (var ii = 0; i < employees.length; ii++) {
                var employee = employees[ii];
                employee.modified_at = event_date;
            }

            xw_collection.update(employees, function () {
                callback(null, employees);
            });
        }
    });
};

exports.CrosswordProvider = CrosswordProvider;

//
// TODO: Avoid the need to duplicate this from /jchpft-ui/features/xw-model/ by sharing code.
//

var __hasProp = {}.hasOwnProperty,
    __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

exports.DocumentFactory = (function() {
    function DocumentFactory() {
        return this;
    }

    DocumentFactory.prototype.newTripleNoTwentyTicketDocument = function(ticketId) {
        var retVal;
        retVal = new V1TriplingTicketDocument(ticketId, 'DESCRIBE', '_________________________________________________________________________________________________________________________', '__________________', -1, '_____', -1);
        return retVal;
    };

    DocumentFactory.prototype.newTripleWithSpotTicketDocument = function(ticketId) {
        var retVal;
        retVal = new V2TriplingTicketDocument(ticketId, 'DESCRIBE', '_________________________________________________________________________________________________________________________', '__________________', -1, '_____', -1, -1);
        return retVal;
    };

    DocumentFactory.prototype.newFiveXTicketDocument = function(ticketId) {
        var retVal;
        retVal = new FiveXTicketDocument(ticketId, 'DESCRIBE', '_________________________________________________________________________________________________________________________', '__________________', -1, -1, -1);
        return retVal;
    };

    DocumentFactory.prototype.loadTicketDocument = function(obj) {
        if (obj.hasOwnProperty('bonusWord')) {
            if (obj.hasOwnProperty('spotValue')) {
                return loadTripleWithSpotTicketDocument(obj.ticketId, obj.lifeStage, obj.content, obj.yourLetters, obj.payoutValue, obj.bonusWord, obj.bonusValue, obj.spotValue);
            } else {
                return loadTripleNoTwentyTicketDocument(obj.ticketId, obj.lifeStage, obj.content, obj.yourLetters, obj.payoutValue, obj.bonusWord, obj.bonusValue);
            }
        } else if (obj.hasOwnProperty('multiplier')) {
            return loadFiveXTicketDocument(obj.ticketId, obj.lifeStage, obj.content, obj.yourLetters, obj.payoutValue, obj.multiplier, obj.spotValue);
        }
        return null;
    };

    DocumentFactory.prototype.loadTripleNoTwentyTicketDocument = function(ticketId, lifeStage, content, yourLetters, payoutValue, bonusWord, bonusValue) {
        return new V1TriplingTicketDocument(ticketId, lifeStage, content, yourLetters, payoutValue, bonusWord, bonusValue);
    };

    DocumentFactory.prototype.loadTripleWithSpotTicketDocument = function(ticketId, lifeStage, content, yourLetters, payoutValue, bonusWord, bonusValue, spotValue) {
        return new V2TriplingTicketDocument(ticketId, lifeStage, content, yourLetters, payoutValue, bonusWord, bonusValue, spotValue);
    };

    DocumentFactory.prototype.loadFiveXTicketDocument = function(ticketId, lifeStage, content, yourLetters, payoutValue, multiplier, spotValue) {
        return new FiveXTicketDocument(ticketId, lifeStage, content, yourLetters, payoutValue, multiplier, spotValue);
    };

    return DocumentFactory;
})();
DocumentFactory = exports.DocumentFactory;

exports.AbstractTicketDocument = AbstractTicketDocument = (function() {
    AbstractTicketDocument.identityRegex = /\d{3,4}:\d{6,7}\-\d{3}/;

    function AbstractTicketDocument(ticketId, lifeStage, content, yourLetters, payoutValue) {
        this.ticketId = ticketId;
        this.lifeStage = lifeStage;
        this.content = content;
        this.yourLetters = yourLetters;
        this.payoutValue = payoutValue;
        if (!AbstractTicketDocument.identityRegex.test(ticketId)) {
            throw 'InvalidArgumentException: ticketId = ' + ticketId + ' is malformed!';
        }
        if (lifeStage !== 'DESCRIBE' && lifeStage !== 'PLAY' && lifeStage !== 'FINAL') {
            throw 'InvalidArgumentException: lifeStage must be "DESCRIBE", "PLAY", or "FINAL".  lifeStage = ' + lifeStage;
        }
        if (lifeStage === 'FINAL' && yourLetters.search('_') > -1) {
            throw 'InvalidArgumentException.  Cannot be Final with unresolved values in yourLetters.  yourLetters = ' + yourLetters;
        }
        if (lifeStage === 'DESCRIBE' && yourLetters.search(/[^_]/) > -1) {
            throw 'InvalidArgumentException.  Cannot be have anything resolved in yourLetters while still describing ticket.  yourLetters = ' + yourLetters;
        }
        if (lifeStage === 'FINAL' && payoutValue === -1) {
            throw 'InvalidArgumentException.  Cannot be Final unresolved payoutValue.  payoutValue = ' + payoutValue;
        }
        if (lifeStage === 'FINAL' && payoutValue > 16) {
            throw 'InvalidArgumentException.  Final payoutValue cannot be out-of-bounds.  payoutValue = ' + payoutValue;
        }
        if (lifeStage !== 'FINAL' && payoutValue !== -1) {
            throw 'InvalidArgumentException.  Final payoutValue cannot set until ticket is fully resolved.  payoutValue = ' + payoutValue;
        }
    }

    return AbstractTicketDocument;
})();

exports.V1TriplingTicketDocument = V1TriplingTicketDocument = (function(_super) {
    __extends(V1TriplingTicketDocument, _super);

    function V1TriplingTicketDocument(ticketId, lifeStage, content, yourLetters, payoutValue, bonusWord, bonusValue) {
        this.bonusWord = bonusWord;
        this.bonusValue = bonusValue;
        V1TriplingTicketDocument.__super__.constructor.call(this, ticketId, lifeStage, content, yourLetters, payoutValue);
        if (lifeStage !== 'DESCRIBE' && bonusWord.search(/[^_]/) > -1) {
            throw 'InvalidArgumentException.  Cannot be have anything resolved in bonusWord after describing ticket.  bonusWord = ' + bonusWord;
        }
    }

    return V1TriplingTicketDocument;
})(exports.AbstractTicketDocument);

exports.V2TriplingTicketDocument = V2TriplingTicketDocument = (function(_super) {
    __extends(V2TriplingTicketDocument, _super);

    function V2TriplingTicketDocument(ticketId, lifeStage, content, yourLetters, payoutValue, bonusWord, bonusValue, spotValue) {
        this.bonusWord = bonusWord;
        this.bonusValue = bonusValue;
        this.spotValue = spotValue;
        V2TriplingTicketDocument.__super__.constructor.call(this, ticketId, lifeStage, content, yourLetters, payoutValue);
    }

    return V2TriplingTicketDocument;
})(exports.AbstractTicketDocument);

exports.FiveXTicketDocument = FiveXTicketDocument = (function(_super) {
    __extends(FiveXTicketDocument, _super);

    function FiveXTicketDocument(ticketId, lifeStage, content, yourLetters, payoutValue, mutiplier, spotValue) {
        this.mutiplier = mutiplier;
        this.spotValue = spotValue;
        FiveXTicketDocument.__super__.constructor.call(this, ticketId, lifeStage, content, yourLetters, payoutValue);
    }

    return FiveXTicketDocument;
})(exports.AbstractTicketDocument);
