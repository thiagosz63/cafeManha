export interface Colaborador {
    id: number;
    nome: string;
    cpf: string;
    senha: string;
}
export interface ColaboradorPorPaginas {
    content?: Colaborador[];
    last: boolean;
    totalPages: number;
    totalElements: number;
    size?: number;
    number: number;
    first: boolean;
    numberOfElements?: number;
    empty?: boolean
}