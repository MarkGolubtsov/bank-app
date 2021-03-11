import {ItemsStore} from 'app/store/ItemsStore';
import {computed} from 'mobx';


export class ContractsStore {
    private _contractStore: ItemsStore<any>;

    constructor(contractStore: ItemsStore<any>) {
        this._contractStore = contractStore;
    }

    @computed
    getItemsByUserID(id: number) {
        return this._contractStore.items.filter(item => Number(item.clientId) === Number(id));
    }

    get items() {
        return this._contractStore.items;
    }
}