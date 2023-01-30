import Knex from 'knex';
import DatabaseConfig from './config/DatabaseConfig';

async function connect() {
    const config = DatabaseConfig.config;
    
    const knex = await Knex({
        client: 'pg',
        connection: {
            user: config.connection.user,
            password: config.connection.password,
            host: config.connection.host,
            port: Number(config.connection.port),
            database: config.connection.database
        },
        pool: {
            min: Number(config.pool.min),
            max: Number(config.pool.max),
            idleTimeoutMillis: Number(config.pool.idle)
        },
        acquireConnectionTimeout: 2000
    });
   
    return (await knex);
}

export default connect;