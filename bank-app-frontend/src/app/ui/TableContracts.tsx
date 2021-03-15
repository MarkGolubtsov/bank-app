import React from 'react';
import {Table} from 'antd';
import {observer} from 'mobx-react-lite';
import {User} from 'app/entity/User';
import {Button} from 'antd/es';

type TableContractProps = {
    items: any,
    users: User[];
    onWithdraw: (id: string) => void;
    onShowCreditData: (id: string) => void;
}

export const TableContract = observer(({items, users, onWithdraw, onShowCreditData}: TableContractProps) => {

    const handleWithdraw = (contractNumber: string) => {
        onWithdraw(contractNumber)
    }

    const columns = [
        {
            title: 'Contract number',
            dataIndex: 'contractNumber',
            key: 'contractNumber',

        },
        {
            title: 'Currency',
            dataIndex: 'currecncy',
            key: 'currecncy',
        },
        {
            title: 'Start',
            dataIndex: 'startDate',
            key: 'startDate',
        },
        {
            title: 'End',
            dataIndex: 'endDate',
            key: 'endDate',
        },
        {
            title: 'Amount',
            dataIndex: 'amount',
            key: 'amount',
        },
        {
            title: 'Percents',
            dataIndex: 'percents',
            key: 'percents',
            render: (text: string) => (`${text}%`)
        },
        {
            title: 'Contract type',
            dataIndex: 'contractType',
            key: 'contractType',
        },
        {
            title: 'Contract name',
            dataIndex: 'contractName',
            key: 'contractName',
            render: (_: any, record: any) => (record.contractType === 'DEPOSIT' ? record['depositName'] : record['creditName'])
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
        {
            title: 'Status',
            dataIndex: 'status',
            key: 'status',
        },
        {
            title: 'Action',
            dataIndex: 'action',
            key: 'action',
            render: (_: any, record: any) => {
                if (record.contractType === 'DEPOSIT' && record["depositName"] === 'Last' && record.status === 'ACTIVE') {
                    return <Button danger onClick={()=>handleWithdraw(record.contractNumber)}>
                        Withdraw
                    </Button>
                }
                if (record.contractType === 'CREDIT') {
                    return <Button color={'green'} onClick={()=>onShowCreditData(record.contractNumber)}>
                        Show data
                    </Button>
                }
            }
        },
    ];
    return (
        <Table dataSource={items} columns={columns}/>
    )
})