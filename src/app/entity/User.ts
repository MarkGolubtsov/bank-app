import {Passport} from 'app/entity/Passport';
import {Work} from 'app/entity/Work';
import {Entity} from 'app/entity/Entity';
import {SelectValue} from 'app/entity/SelectValue';

export interface User extends Entity{

    surname: string,

    name: string,

    patronymic: string,

    birthday: Date,

    passport: Passport,

    placeOfBirth: string,

    cityOfActualResidence: SelectValue,

    addressOfActualResidence: string,

    mobilePhoneNumber: string,

    homePhoneNumber: string,

    email: string,

    work: Work,

    maritalStatus: SelectValue,

    nationality: SelectValue,

    disability: SelectValue,

    pensioner: boolean,

    monthlyIncome: string,

    military: boolean
}