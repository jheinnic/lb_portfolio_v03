/**
 * Module dependencies.
 */

var express = require('express');
var http = require('http');
var path = require('path');
var lessMiddleware = require('less-middleware');
var coffeeMiddleware = require('coffee-middleware');

var routes = require('./routes');
// var user = require('./routes/user');
// var EmployeeProvider = require('./lib/employeeprovider').EmployeeProvider;

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

    // "Boilerplate you should already have", but I didn't...
    app.use(express.bodyParser({keepExtensions: true, uploadDir: '/w/e/my/files'}));
    app.use(express.methodOverride());
    app.use(app.router);

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
    }

    //
    // Routes
    //
    app.get('/', routes.index);
});

http.createServer(app).listen(app.get('port'), function () {
    console.log('Express server listening on port ' + app.get('port'));
});
