import { Colaborador } from "./colaborado";

export interface Itens{
    id: number;
    nome: string;
    status: string
    colaborador: Colaborador        
}

export interface ItensPaginado {
    content?: Itens[];
    last: boolean;
    totalPages: number;
    totalElements: number;
    size?: number;
    number: number;
    first: boolean;
    numberOfElements?: number;
    empty?: boolean
}