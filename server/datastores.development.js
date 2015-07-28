module.exports = {
  env_db: {
    connector: 'mongodb',
    hostname: process.env.DB_HOST || 'localhost',
    port: process.env.DB_PORT || 27017,
    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: 'todo-example',
  },
  db: {
    connector: 'mongodb',
    database: 'portfolio',
    hostname: 'localhost',
    port: 27017,
    user: 'jheinnic',
    password: 'abcd1234'
  }
};
