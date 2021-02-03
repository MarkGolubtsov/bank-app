import {ItemsStore} from 'app/store/ItemsStore';
import {User} from 'app/entity/User';
import {endpoints} from 'app/api/endpoints';
import {SelectValue} from 'app/entity/SelectValue';

export class RootStore {
    private readonly _usersStore = new ItemsStore<User>(endpoints.users);
    private readonly _citiesStore = new ItemsStore<SelectValue>(endpoints.cities);

    get usersStore() {
        return this._usersStore;
    }

    get citiesStore() {
        return this._citiesStore;
    }
}