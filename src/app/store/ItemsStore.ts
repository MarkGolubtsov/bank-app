import {makeAutoObservable, runInAction, toJS} from "mobx";
import {RequestService} from 'app/api/RequestService';


export class ItemsStore<T> {

    private _items: T[] = [];
    private readonly endpoint: string = '';

    constructor(endpoint: string) {
        makeAutoObservable(this);
        this.endpoint = endpoint;
    }

    public fetchItems() {
        RequestService.get(this.endpoint).then((response) => {
            runInAction(() => {
                this._items = response.data.payload;
            })
        });
    }

    get items(): T[] {
        return toJS(this._items);
    }

}