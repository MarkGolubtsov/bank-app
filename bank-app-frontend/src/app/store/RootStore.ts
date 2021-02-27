import {ItemsStore} from 'app/store/ItemsStore';
import {User} from 'app/entity/User';
import {endpoints} from 'app/api/endpoints';
import {SelectValue} from 'app/entity/SelectValue';

export class RootStore {
    private readonly _usersStore = new ItemsStore<User>(endpoints.users);
    private readonly _citiesStore = new ItemsStore<SelectValue>(endpoints.cities);
    private readonly _nationalities = new ItemsStore<SelectValue>(endpoints.nationalities);
    private readonly _martialStatuses = new ItemsStore<SelectValue>(endpoints.martialStatuses);
    private readonly _disabilities = new ItemsStore<SelectValue>(endpoints.disabilities);

    get usersStore() {
        return this._usersStore;
    }

    get citiesStore() {
        return this._citiesStore;
    }

    get nationalitiesStore() {
        return this._nationalities;
    }

    get martialStatusesStore() {
        return this._martialStatuses;
    }

    get disabilitiesStore() {
        return this._disabilities;
    }
}