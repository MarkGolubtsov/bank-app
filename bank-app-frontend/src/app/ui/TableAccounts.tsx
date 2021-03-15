import React from 'react';
import {Table} from 'antd';
import {User} from 'app/entity/User';

type TableAccountsProps = {
    items: any[],
    users: User[]
}

export const TableAccounts = ({items, users}: TableAccountsProps) =>{
    const columns = [
        {
            title: 'Account code',
            dataIndex: 'accountCode',
            key: 'accountCode',

        },
        {
            title: 'Account Number',
            dataIndex: 'accountNumber',
            key: 'accountNumber',
        },
        {
            title: 'Account type',
            dataIndex: 'accountType',
            key: 'accountType',
        },
        {
            title: 'Debit',
            dataIndex: 'debit',
            key: 'debit',
        },
        {
            title: 'Credit',
            dataIndex: 'credit',
            key: 'credit',
        },
        {
            title: 'surplus',
            dataIndex: 'surplus',
            key: 'surplus',
        },
        {
            title: 'Account status',
            dataIndex: 'accountStatus',
            key: 'accountStatus',
        },

        {
            title: 'User info',
            dataIndex: 'user',
            key: 'user',
            render: (_: any, record: any) => {
                const userId = record['clientId'];
                const user = users.find(user => user.id == userId);
                if (user) {
                    return `${user.surname}  ${user.name}`;
                } else {
                    return ''
                }
            }
        },
    ];
    return (
        <Table dataSource={items} columns={columns} />
    )
}