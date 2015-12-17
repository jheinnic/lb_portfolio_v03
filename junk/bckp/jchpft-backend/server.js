/**
 * Module dependencies.
 */

var express = require('express');
var jade = require('jade');
var http = require('http');
var path = require('path');
var fs = require('fs');
var lessMiddleware = require('less-middleware');
var coffeeMiddleware = require('coffee-middleware');

var CrosswordProvider = require('./lib/xwprovider').CrosswordProvider;
var routes = require('./routes');

// var user = require('./routes/user');
// var xwRoutes = require('./routes/xw');

var app = express();

/**
 * Utility shorthand for appending one element to _dirName
 * @param subPath
 * @returns {*|string}
 */
function relPath(subPath) {
    return path.join(__dirname, '..', 'jchpft-ui', subPath);
}

app.configure(function () {
    // all environments
    app.set('port', process.env.PORT || 3000);
    app.set('views', path.join(__dirname, 'views'));
    app.set('view engine', 'jade');
    app.engine('html', jade.__express);
    app.locals.basedir = path.join(__dirname, 'views');

    // What do these do?
    app.use(express.favicon());
    if ('development' == app.get('env')) {
        app.use(express.logger('dev'));
    } else if ('production' == app.get('env')) {
        app.use(express.logger('prd'));
    } else {
        app.use(express.logger(app.get('env')));
    }
    app.use(express.json());
    app.use(express.urlencoded());
    app.use(app.router);

    // Boilerplate I read somewhere that "you should already have", but I do not yet want.
    // app.use(express.bodyParser({keepExtensions: true, uploadDir: '/w/e/my/files'}));
    // app.use(express.methodOverride());

    // Compile .less and .coffee files, then map resources under /public to static files relative to __dirname/public.
    app.use('/app', express.static(relPath('features')));
    app.use('/app', lessMiddleware({
        prefix: '/app',
        src: relPath('features'),
        paths: [
            relPath(path.join('vendor', 'bootstrap', 'less')),
            relPath('features')
        ],
        force: true,
        debug: true
    }));
    app.use('/app', coffeeMiddleware({
        prefix: '/app',
        src: relPath('features'),
        force: true,
        debug: true
    }));
    app.use('/app', express.static(relPath('components')));
    app.use('/app', coffeeMiddleware({
        prefix: '/app',
        src: relPath('components'),
        force: true,
        debug: true
    }));
    app.use('/app', express.static(relPath('vendor')));
    app.use('/app', express.static(path.join(__dirname, 'jchpft-common', 'components')));

    // Establish a static path root for bower component dependencies.
    // Map URIs that begin with /img to Bootstrap's img directory.
    app.use('/vendor', express.static(relPath('vendor')));
    app.use('/img', express.static(relPath(path.join('vendor', 'bootstrap', 'img'))));

    // development only
    if ('development' == app.get('env')) {
        app.use(express.errorHandler());
    } else if (app.get('env') === 'production') {
        // production only
        // TODO
    }

    //
    // Routes
    //
    app.get('/', routes.index);
    app.get('/hack001', routes.hack001);

    // TODO: This should have a TicketID variable match in the path
    // TODO: Express the game ID below as /crosswords/game/999/ticket/123456-001 and use the '999' portion of the path to
    //       determine what type of ticket is being created.
    app.get('/crosswords', routes.listCrosswords);
    app.post('/crossword/new', routes.postNewCrossword);

    app.get('/test', function(req, res){
        res.render('index.jade', { title: 'Express' });
    });
});

http.createServer(app).listen(app.get('port'), function () {
    console.log('Express server listening on port ' + app.get('port'));
});
