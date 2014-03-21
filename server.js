
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
    return path.join(__dirname, subPath);
}

app.configure( function() {
    // all environments
    app.set('port', process.env.PORT || 3000);
    app.set('views', relPath('views'));
    app.set('view engine', 'jade');
    app.locals.basedir = relPath('views');

    // What do these do?
    app.use(express.favicon());
    if( 'development' == app.get('env')) {
        app.use(express.logger('dev'));
    } else if( 'production' == app.get('env')) {
        app.use(express.logger('prd'));
    } else {
        app.use(express.logger(app.get('env')));
    }
    app.use(express.json());
    app.use(express.urlencoded());

    // "Boilerplate you should already have"?
    app.use(express.bodyParser({keepExtensions: true, uploadDir: '/my/files'}));
    app.use(express.methodOverride());
    app.use(app.router);

    // Compile .less and .coffee files, then map resources under /public to static files relative to __dirname/public.
    app.use('/public', lessMiddleware({
        src:relPath('public'),
        paths:[
            path.join(__dirname, 'components', 'bootstrap', 'less'),
            path.join(__dirname, 'public', 'stylesheets')
        ],
        prefix:'/public',
        force:true,
        debug:true
    }));
    app.use('/public', coffeeMiddleware({
        src:relPath('public'),
        prefix:'/public',
        force:true,
        debug:true
    }));
    app.use('/public', express.static(relPath('public')));
    app.use('/public', coffeeMiddleware({
        src:relPath('common'),
        prefix:'/public',
        force:true,
        debug:true
    }));
    app.use('/public', express.static(relPath('common')));
    app.use('/public', express.static(relPath('components')));

    // Establish a static path root for bower component dependencies.
    app.use('/components', express.static(relPath('components')));

    // Map URIs that begin with /img to Bootstrap's img directory.
    app.use('/img', express.static(path.join(__dirname, 'components', 'bootstrap', 'img')));

    // development only
    if ('development' == app.get('env')) {
      app.use(express.errorHandler());
    }

    //
    // Routes
    //
    app.get('/', routes.index);
});

http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});
