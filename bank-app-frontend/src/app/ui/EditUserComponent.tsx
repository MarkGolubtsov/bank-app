import React, {useContext} from 'react';
import {RouteComponentProps} from 'react-router';
import {UserForm} from 'app/ui/form/UserForm';
import {RootStoreContext} from 'app/RootStoreContext';
import {User} from 'app/entity/User';


interface MatchId {
    id?: string
}

export const EditUserComponent = (props: RouteComponentProps<MatchId>) => {

    const id = props.match.params.id;
    const {usersStore} = useContext(RootStoreContext);

    const onSave = (user: User) => {
        console.log(user);
        usersStore.createItem(user).then(()=>{

        });
    }

    const onCancel = () => {
        props.history.goBack();
    }

    return (
        <UserForm onSave={onSave} onCancel={onCancel}/>
    )
}