import {ItemsStore} from 'app/store/ItemsStore';
import {User} from 'app/entity/User';
import {endpoints} from 'app/api/endpoints';

export class RootStore {
    private readonly _usersStore = new ItemsStore<User>(endpoints.users);
    private readonly _citiesStore = new ItemsStore<string>(endpoints.cities);
    private readonly _nationalities = new ItemsStore<string>(endpoints.nationalities);
    private readonly _martialStatuses = new ItemsStore<string>(endpoints.martialStatuses);
    private readonly _disabilities = new ItemsStore<string>(endpoints.disabilities);
    private readonly _gender = new ItemsStore<string>(endpoints.gender);
    private readonly _countries = new ItemsStore<string>(endpoints.countries)
    private readonly _currency = new ItemsStore<string>(endpoints.currency)

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

    get genderStore() {
        return this._gender;
    }

    get countries() {
        return this._countries;
    }
    get currency() {
        return this._currency;
    }
}