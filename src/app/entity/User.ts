import {Passport} from 'app/entity/Passport';
import {Work} from 'app/entity/Work';
import {Entity} from 'app/entity/Entity';

export interface User extends Entity{

    surname: string,

    name: string,

    patronymic: string,

    birthday: Date,

    passport: Passport,

    placeOfBirth: string,

    cityOfActualResidence: string,

    addressOfActualResidence: string,

    mobilePhoneNumber: string,

    homePhoneNumber: string,

    email: string,

    work: Work,

    maritalStatus: string,

    nationality: string,

    disability: string,

    pensioner: boolean,

    monthlyIncome: string

}