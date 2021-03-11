export class Routes {

    public static users = '/users';
    public static createUser = '/users/create';
    public static createDepositAgreement(id: string){
        return '/agreement/deposit/clients/' + id;
    };
    public static editUser(id: string) {
        return '/users/edit/' + id
    }

}