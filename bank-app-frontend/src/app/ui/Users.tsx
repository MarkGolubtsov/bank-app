import React, {useContext, useEffect} from 'react';
import {observer} from 'mobx-react-lite';
import {RootStoreContext} from 'app/RootStoreContext';
import {UserComponent} from 'app/ui/UserComponent';
import {useHistory} from 'react-router';
import {Routes} from 'app/constants/Routes';

export const Users = observer(() => {

    const {usersStore} = useContext(RootStoreContext);

    useEffect(() => {
        usersStore.fetchItems();
    }, [usersStore])

    const history = useHistory();

    const handleEdit = (id: string) => {
        history.push(Routes.editUser(id));
    }
    const handleDelete = (id: string) => {
        usersStore.delete(id).then(() => {

        });
    }

    return (
        <div className="site-layout-background" style={{padding: 24}}>
            {usersStore.items.map((item) => {
                return <UserComponent key={item.id} user={item} onDelete={handleDelete} onEdit={handleEdit}/>
            })}
        </div>
    )
})