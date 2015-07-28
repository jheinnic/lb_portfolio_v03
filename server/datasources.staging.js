module.exports = {
  db: {
    connector: 'mongodb',
    database: 'portfolio',
    hostname: process.env.DB_HOST || 'localhost',
    port: process.env.DB_PORT || 27017,
    user: process.env.DB_USER || 'jheinnic',
    password: process.env.DB_PASSWORD || 'abcd1234'
  }
};
