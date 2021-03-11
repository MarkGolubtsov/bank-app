import React, {useContext} from 'react';
import {RootStoreContext} from 'app/RootStoreContext';
import {User} from 'app/entity/User';
import {UserForm} from 'app/ui/form/UserForm';
import {useHistory} from 'react-router';
import {Routes} from 'app/constants/Routes';


export const CreateUSerForm = () =>{
    const {usersStore} = useContext(RootStoreContext);
    const history = useHistory();

    const onSave = (user: User) => {
        usersStore.createItem(user).then(() => {
            history.push(Routes.users);
        }).catch(()=>{

        });
    }

    const onCancel = () => {
        history.push(Routes.users);
    }

    return (
        <UserForm onSave={onSave} onCancel={onCancel}/>
    )
}