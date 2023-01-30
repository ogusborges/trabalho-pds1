import FormularioRepository from "../repository/FormularioRepository";
import RespostaFormRepository, { Resposta } from "../repository/RespostaFormRepository";

const repos = async () => {
    return {
        formRepo: FormularioRepository.createRepository(),
        respostaFormRepo: RespostaFormRepository.createRepository()
    };
};

async function createAnswers(formId: number, answers: {values: any}) {
    const {formRepo, respostaFormRepo} = await repos();
    
    const form = (await formRepo).find(
        {cod_formulario: formId}
    );

    if (form == undefined) {
        return undefined;
    }

    if (!answers) {
        return undefined;
    }

    let answerList: Resposta[] = [];

    for(let [question, answer] of Object.entries(answers.values)) {
        answerList.push(
            {cod_formulario: formId, cod_campo: question, resposta: answer} as Resposta
        );
    }

    const resultado = await (await respostaFormRepo).create(answerList as Resposta[]);


    return resultado;
}

async function getAnswers(formId: number) {
    if (!formId) {
        return undefined;
    }

    const _repos = await repos();
    const respostaFormRepo = _repos['respostaFormRepo'];
    
    const resultado = await (await respostaFormRepo).findAll({cod_formulario: formId});
    
    return resultado;
}


export default {createAnswers, getAnswers};