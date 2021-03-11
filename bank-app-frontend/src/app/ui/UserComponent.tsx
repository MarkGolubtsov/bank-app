import React from 'react';
import {User} from 'app/entity/User';
import {Card} from 'antd';
import Meta from 'antd/es/card/Meta';
import {DiffOutlined, DeleteFilled, EditOutlined} from '@ant-design/icons';

type UserProps = {
    user: User,
    onEdit: (id: string) => void;
    onDelete: (id: string) => void;
    onCreateDep: (id: string) => void;
}

export const UserComponent = ({user, onDelete, onEdit, onCreateDep}: UserProps) => {

    const titleCard = `${user.surname} ${user.name} ${user.patronymic}`;

    const handleEdit = () => {
        onEdit(user.id)
    }

    const handleDelete = () => {
        onDelete(user.id);
    }
    const handleCreateDep = () => {
        onCreateDep(user.id);
    }

    return (
        <Card
            style={{width: 300}}
            actions={[<DiffOutlined onClick={handleCreateDep} key={'createDep'}/>,
                <EditOutlined onClick={handleEdit} key="edit"/>,
                <DeleteFilled key={'delete'} onClick={handleDelete}/>]}>
            <Meta
                title={titleCard}
            />
        </Card>
    )
}
