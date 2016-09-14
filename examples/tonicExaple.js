(function() {
  'use strict';

  var app = require('./server');
  var contentString =
    'cheap__work' +
    'u_p_each__e' +
    's_i_n__ahoy' +
    'tactical_d_' +
    'o___n__e_d_' +
    'majesty_e_s' +
    '__a_u_o_c_a' +
    'sew_l_n_h_n' +
    'o__landlord' +
    'also__e___a' +
    'r__g__rural';
  var EditorModel = app.models.EditorModel;

  module.exports.app = app;
  module.exports.contentString = contentString;
  module.exports.EditorModel = EditorModel;
  module.exports.makeE = function makeE() {
    return EditorModel.init(contentString);
  };
}).call(this || window);
