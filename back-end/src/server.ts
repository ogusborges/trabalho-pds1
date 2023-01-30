import express, { Application } from "express";
import e, {Request, Response} from 'express';
import cors, { CorsOptions } from 'cors';
import RespostaFormRepository from "./repository/RespostaFormRepository";
import RespostaFormController from "./controllers/RespostaFormController";

class App {
    private app: Application;
    private corsOptions;
    private controllers;

    constructor() {
        this.app = express();
        
        this.corsOptions = {
            origin: ['http://localhost:3000']
        };
        
        this.controllers = [
            new RespostaFormController()
        ];

        this.start();
    }

    async getText(req: Request, res: Response) {
        console.log('Recebi uma rqeuisição');
        res.json({message: 'Hello World from Typescript.', enviado: req.body});
        
        // const respostaFormRepository = await RespostaFormRepository.createRepository();
        
        // res.json(
        //     (await respostaFormRepository.findAll())
        // )
    }

    start() {
        this.app.use(cors(this.corsOptions));
        this.app.use(express.json());
        this.app.listen(3333, () => 'Estou escutando na porta 3333.');
        
        this.app.use('/respostaform', this.controllers[0].route);
        this.app.post('/', this.getText);

        
    }
}

new App();