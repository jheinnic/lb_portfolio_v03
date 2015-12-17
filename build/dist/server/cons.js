(function () {
  'use strict';

  var app = module.exports.app = require('./server');
  var contentString = module.exports.contentString = 'cheap__worku_p_each__es_i_n__ahoytactical_d_o___n__e_d_majesty_e_s__a_u_o_c_asew_l_n_h_no__landlordalso__e___ar__g__rural';

  var EditorModel = module.exports.EditorModel = app.models.EditorModel;
  module.exports.makeE = function makeE() {
    var e = EditorModel.init(contentString);
    return e;
  };
}).call();
