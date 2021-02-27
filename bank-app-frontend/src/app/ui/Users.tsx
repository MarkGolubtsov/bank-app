import React, {useContext, useEffect} from 'react';
import {observer} from 'mobx-react-lite';
import {RootStoreContext} from 'app/RootStoreContext';

export const Users = observer(() => {

    const {usersStore} = useContext(RootStoreContext);

    useEffect(() => {
        usersStore.fetchItems();
    }, [usersStore])

    return (
        <div>
            {usersStore.items.map((item) => {
                return <div>{JSON.stringify(item)}</div>
            })}
        </div>)
})