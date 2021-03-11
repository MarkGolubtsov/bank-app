import React, {useContext} from 'react';
import {RouteComponentProps} from 'react-router';
import {UserForm} from 'app/ui/form/UserForm';
import {RootStoreContext} from 'app/RootStoreContext';
import {User} from 'app/entity/User';


export interface MatchId {
    id?: string
}

export const EditUserComponent = (props: RouteComponentProps<MatchId>) => {
    const id = props.match.params.id;
    const {usersStore} = useContext(RootStoreContext);

    const onSave = (user: User) => {
        usersStore.updateItem(user.id, user).then(() => {
            props.history.goBack();
        });
    }

    const onCancel = () => {
        props.history.goBack();
    }

    const user = usersStore.items.find(user => Number(user.id) === Number(id));
    return (
        <UserForm user={user} onSave={onSave} onCancel={onCancel}/>
    )
}