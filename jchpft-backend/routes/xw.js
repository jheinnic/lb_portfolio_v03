var DocumentFactory = require('../lib/xwprovider').DocumentFactory

var crosswordDAO = new CrosswordProvider('localhost', 27017);

exports.listCrosswords =
    function(req, res){
        crosswordDAO.findAll(function(error, crosswords){
    //        res.render('index', {
    //            title: 'crosswords',
    //            crosswords: crosswords
    //        });
            res.render(crosswords);
        });
    };

//exports.newCrosswordForm =
//    app.get('/crossword/new', function(req, res) {
//        res.render('crossword_new', {
//            title: 'New crossword',
//            cause: 'Just Be'
//        });
//    });

exports.postNewCrossword =
    function(req, res){
        crosswordDAO.create(
            DocumentFactory.newTripleNoTwentyTicketDocument('999:123456-001'),
            function( error, docs) {
                res.redirect('/');
            }
        );
    };

// TODO: Get Ticket by TicketID

// TODO: Update Ticket by TicketID