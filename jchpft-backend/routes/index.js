
/*
 * GET home page.
 */

exports.index = function(req, res){
  res.render('index', { title: 'Express' });
};
exports.partials = function(req,res) {
    console.log(req.path + " -> " + req.path.substring(1, req.path.length-5), 5);
    // Example: Turn '/partials/portfolio/view.html' into 'partials/portfoilio/view'
    res.render(req.path.substring(1, req.path.length-5), {});
};