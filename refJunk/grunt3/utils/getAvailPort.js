'use strict';

module.exports = function getAvailablePort(startPort) {
  function isPortOpen(port) {
    // Test for Windows (true) or Linux (false)
    var cmd;
    if (/^win/.test(process.platform)) {
      cmd = 'netstat -an | find /i ":' + port + '" | find /i "listening"';
    } else {
      cmd = 'lsof -i:' + port + ' | tail -n 1 | awk \'{print $2}\'';
    }

    // Return true iff command executuion produces nothing on standard out.
    return ! require('shelljs').exec(cmd, {silent:true}).output;
  }

  while (!isPortOpen(startPort)) {
    startPort += 1;
  }

  return startPort;
};
