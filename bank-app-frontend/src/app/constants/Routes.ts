export class Routes {

    public static users = '/users';
    public static createUser = '/users/create';
    public static contracts = '/contracts';
    public static accounts = '/accounts';
    public static createDepositAgreement(id: string){
        return '/agreement/deposit/clients/' + id;
    };

    public static createCreditAgreement(id: string){
        return '/agreement/credit/clients/' + id;
    };
    public static editUser(id: string) {
        return '/users/edit/' + id
    }

    public static showDateCredit(id: string) {
        return  '/contracts/' + id;
    }
}