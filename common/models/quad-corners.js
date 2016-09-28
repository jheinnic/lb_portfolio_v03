(
  function () {
    'use strict';

    module.exports = function (QuadCorners) {
      QuadCorners.prototype.toPolylines = function toPolylines() {
        return [
          [[this.topLeftY,this.topLeftX]],
          [[this.topRighttY,this.topRightX]],
          [[this.bottomRightY,this.bottomRightX]],
          [[this.bottomLeftY,this.bottomRightY]]
        ];
      };
    };
  }
).call();
