
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
var EmployeeProvider = require('./lib/employeeprovider').EmployeeProvider;

var app = express();

// all environments
app.set('port', process.env.PORT || 3000);
app.set('views', relPath('views'));
app.set('view engine', 'jade');
app.locals.basedir = relPath('views');

// What do these do?
app.use(express.favicon());
app.use(express.logger('dev'));
app.use(express.json());
app.use(express.urlencoded());

// "Boilerplate you should already have"?
app.use(express.bodyParser());
app.use(express.methodOverride());
app.use(app.router);

// Compile .less and .coffee files, then map resources under /public to static files relative to __dirname/public.
app.use(lessMiddleware({src:relPath('public'), force:true, debug:true}));
app.use(coffeeMiddleware({src:relPath('public'), force:true, debug:true}));
app.use('/public', express.static(relPath('public')));

// Establish a static path root for bower component dependencies.
app.use('/components', express.static(relPath('/components')));

// development only
if ('development' == app.get('env')) {
  app.use(express.errorHandler());
}


var employeeDAO = new EmployeeProvider('localhost', 27017);

//Routes

app.get('/employees', function(req, res){
  employeeDAO.findAll(function(error, emps){
      res.render('index', {
            title: 'Employees',
            employees:emps
        });
  });
});

app.get('/employee/new', function(req, res) {
    res.render('employee_new', {
        title: 'New Employee',
        cause: 'Just Be'
    });
});

//save new employee
app.post('/employee/new', function(req, res){
    employeeDAO.save({
        title: req.param('title'),
        name: req.param('name')
    }, function( error, docs) {
        res.redirect('/');
    });
});

app.get('/', routes.index);
app.get('/partials/*', routes.partials);
// app.get('/users', user.list);

http.createServer(app).listen(app.get('port'), function(){
  console.log('Express server listening on port ' + app.get('port'));
});

function relPath(subPath) {
    return path.join(__dirname, subPath);
}
