import {Knex} from 'knex';
import connect from '../database';
import DatabaseConfig from '../config/DatabaseConfig';
import Repository from './Repository';

const schema = DatabaseConfig.schema;

interface Formulario {
    cod_formulario? : number,
    titulo? : string,
    data_ini? : Date,
    data_fim? : Date,
    descricao? : string
};

class FormularioRepository implements Repository<Formulario> {
    private repo : () => Knex.QueryBuilder;
    
    private constructor (repo: () => Knex.QueryBuilder) {
        this.repo = repo;
    }

    public static createRepository = async () => {
        let repo = await connect();

        let _repo = () => repo
                    .withSchema(schema)
                    .table('formulario');

        return new FormularioRepository(_repo);
    };

    public async find(input: Formulario) {
        return (await this.repo().select().where(input) as Formulario[])[0];
    }

    public async findAll(input?: Formulario) {
        const formularios = this.repo().select();
    
        if (input) {
            formularios.where(input);
        }

        return (await formularios as Formulario[]);
    }
}


export default FormularioRepository;