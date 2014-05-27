// var employeeDAO = new EmployeeProvider('localhost', 27017);

// exports.listEmployees
app.get('/employees', function(req, res){
    employeeDAO.findAll(function(error, employees){
        res.render('index', {
            title: 'Employees',
            employees: employees
        });
    });
});

// exports.newEmployeeForm
app.get('/employee/new', function(req, res) {
    res.render('employee_new', {
        title: 'New Employee',
        cause: 'Just Be'
    });
});

// exports.postNewEmployee
app.post('/employee/new', function(req, res){
    employeeDAO.save({
        title: req.param('title'),
        name: req.param('name')
    }, function( error, docs) {
        res.redirect('/');
    });
});