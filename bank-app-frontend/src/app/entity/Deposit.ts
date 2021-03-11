import {Moment} from 'moment'

export interface Deposit {
    contractNumber: string,
    depositProgramId: string,
    depositInterest: string,
    currency: string,
    dateStart: Moment,
    dateEnd: Moment,
    termOfTheContract: [Moment, Moment],

}