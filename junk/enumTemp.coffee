# var reg = require('coffee-script/register');
emod = require('./main.coffee');
Enum = emod.Enum;
FooEnum = emod.FooEnum;
Module = emod.Module;

class BarEnum extends Enum
new BarEnum('valueOne')
new BarEnum('valueTwo')
BarEnum.freeze()

exports.BarEnum = BarEnum
exports.Enum = Enum
exports.FooEnum = FooEnum
exports.Module = Module
