export class Routes {

    public static users = '/users';
    public static createUser = '/users/create';

    public static editUser(id: string) {
        return '/users/edit/' + id
    }

}