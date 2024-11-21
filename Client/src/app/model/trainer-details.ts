import {Pokemon} from './pokemon';

export class TrainerDetails {
  constructor(
    public id: string,
    public name:string,
    public age:number,
    public pokemons: Pokemon []
  ) {
  }
}
