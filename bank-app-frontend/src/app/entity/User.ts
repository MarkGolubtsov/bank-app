import {Passport} from 'app/entity/Passport';
import {Work} from 'app/entity/Work';
import {Entity} from 'app/entity/Entity';
import {Address} from 'app/entity/Address';



export interface User extends Entity{

    surname: string,

    name: string,

    patronymic: string,

    birthday: Date,

    gender: string,

    passport: Passport,

    placeOfBirth: string,

    residentialAddress: Address

    homePhoneNumber: string,

    mobilePhoneNumber: string,

    email: string,

    work: Work,

    maritalStatus: string,

    nationality: string,

    disability: string,

    pensioner: boolean,

    monthlyIncome: number,

    military: boolean
}