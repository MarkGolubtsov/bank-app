import {Entity} from 'app/entity/Entity';

export interface Work extends Entity {
    place: string,
    position: string
}