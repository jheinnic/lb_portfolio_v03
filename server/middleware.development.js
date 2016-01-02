module.exports = {
  'routes': {
    'connect-livereload': {
      'params': {
        'hostname': 'localhost',
        'port': process.env.LIVE_RELOAD || 35729
      }
    }
  }
};
