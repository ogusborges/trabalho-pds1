import {Knex} from 'knex';
import connect from '../database';
import DatabaseConfig from '../config/DatabaseConfig';
import Repository from './Repository';

const schema = DatabaseConfig.schema;

export interface Resposta {
    cod_formulario? : number,
    cod_campo? : string,
    resposta? : string
};

class RespostaFormRepository implements Repository<Resposta> {
    private repo : () => Knex.QueryBuilder;
    
    private constructor (repo: () => Knex.QueryBuilder) {
        this.repo = repo;
    }

    public static createRepository = async () => {
        let repo = await connect();

        let _repo = () => repo
                    .withSchema(schema)
                    .table('respostas');

        return new RespostaFormRepository(_repo);
    };

    public async find(input: Resposta) {
        return (await this.repo().select().where(input) as Resposta[])[0];
    }

    public async findAll(input?: Resposta) {
        const respostas = this.repo().select();
    
        if (input) {
            respostas.where(input);
        }

        return (await respostas as Resposta[]);
    }

    public async create(resposta: Resposta[]) {
        let resultado : any = (await this.repo().insert(resposta));
        
        return resultado.rowCount > 0;
    }
}

export default RespostaFormRepository;