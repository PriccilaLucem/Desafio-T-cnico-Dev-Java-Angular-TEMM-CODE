import { Category } from "./category.interface";

export interface Product {
    id: number;
    nome: string;
    descricao: string;
    preco: number;
    quantity: number;
    category: Category;
  }