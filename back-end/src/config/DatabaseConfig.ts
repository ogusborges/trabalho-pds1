import dotEnv from 'dotenv';

dotEnv.config({path: __dirname + '/../.env'});

namespace DatabaseConfig {
    export const config = {
        client: 'postgresql',
        connection: {
            host: process.env.DATABASE_HOST,
            database: process.env.DATABASE_NAME,
            user: process.env.DATABASE_USER,
            password: process.env.DATABASE_PASSWORD,
            port: process.env.DATABASE_PORT
        },
        pool: {
            min: process.env.DATABASE_POOL_MIN,
            max: process.env.DATABASE_POOL_MAX,
            idle: process.env.DATABASE_POOL_IDLE
        }
    }

    export const schema = process.env.DATABASE_SCHEMA as string;
};

export default DatabaseConfig;