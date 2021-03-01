import React from 'react';
import {User} from 'app/entity/User';
import {Card} from 'antd';
import Meta from 'antd/es/card/Meta';
import {DeleteFilled, EditOutlined} from '@ant-design/icons';
import {useHistory} from 'react-router';

type UserProps = {
    user: User,
    onEdit: (id: string) => void;
    onDelete: (id: string) => void;
}

export const UserComponent = ({user, onDelete, onEdit}: UserProps) => {

    const titleCard = `${user.surname} ${user.name} ${user.patronymic}`;
    const history = useHistory();

    const handleEdit = () => {
        onEdit(user.id)
    }

    const handleDelete = () => {
        onDelete(user.id);
    }

    return (
        <Card
            style={{width: 300}}
            actions={[<EditOutlined onClick={handleEdit} key="edit"/>, <DeleteFilled key={'delete'} onClick={handleDelete}/>]}>
            <Meta
                title={titleCard}
            />
        </Card>
    )
}
