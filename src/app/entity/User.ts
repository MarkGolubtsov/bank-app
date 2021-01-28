import {Passport} from 'app/entity/Passport';
import {Work} from 'app/entity/Work';

export interface User {
    id: string,

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