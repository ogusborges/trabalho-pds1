import { Router, Request, Response } from "express";
import FormularioService from "../service/FormularioService";
import FormularioRepository from "../repository/FormularioRepository";
import RespostaFormRepository from "../repository/RespostaFormRepository";

class RespostaFormController {
    public route : Router;
    public path : string;

    constructor () {
        this.route = Router();
        this.path = '/respostaform';
        this.setupRoutes();
    }

    async createRespostas(req: Request, res: Response) {
        const {formId, ...values} = req.body;

        res.json((await FormularioService.createAnswers(formId, values)));
    }

    async getRespostas(req: Request, res: Response) {
        const formId = Number(req.query.formId);
        
        res.json((await FormularioService.getAnswers(formId)))
    }

    async setupRoutes () {
        this.route.post("/", this.createRespostas);
        this.route.get("/", this.getRespostas);
    }
};

export default RespostaFormController;