import React from 'react';
import {User} from 'app/entity/User';
import {Card} from 'antd';
import Meta from 'antd/es/card/Meta';
import {DeleteFilled, DiffOutlined, EditOutlined, SnippetsOutlined} from '@ant-design/icons';

type UserProps = {
    user: User,
    onEdit: (id: string) => void;
    onDelete: (id: string) => void;
    onCreateDep: (id: string) => void;
    onCreateCredit: (id: string) => void;
}

export const UserComponent = ({user, onDelete, onEdit, onCreateDep, onCreateCredit}: UserProps) => {

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
    const handleCreateCredit = () => {
        onCreateCredit(user.id);
    }

    return (
        <Card
            style={{width: 300}}
            actions={[
                <SnippetsOutlined onClick={handleCreateCredit} key={'create credit'}/>,
                <DiffOutlined onClick={handleCreateDep} key={'createDep'}/>,
                <EditOutlined onClick={handleEdit} key="edit"/>,
                <DeleteFilled key={'delete'} onClick={handleDelete}/>]}>
            <Meta
                title={titleCard}
            />
        </Card>
    )
}
