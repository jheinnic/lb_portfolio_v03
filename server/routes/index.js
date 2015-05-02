
/*
 * GET home page.
 */

exports.index = function(req, res){
  res.render('index', { title: 'Express' });
};
exports.hack001 = function(req, res){
  res.render('hack001.jade', { title: 'IHack 001' });
};
exports.partials = function(req,res) {
    console.log(req.path + " -> " + req.path.substring(1, req.path.length-5), 5);
    // Example: Turn '/partials/portfolio/view.html' into 'partials/portfolio/view'
    res.render(req.path.substring(1, req.path.length-5), {});
};

exports.listCrosswords = require('./xw').listCrosswords

exports.postNewCrossword = require('./xw').postNewCrossword
