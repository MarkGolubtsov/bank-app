import React, {useContext, useEffect} from 'react';
import {Button, Col, Row} from 'antd';
import {RootStoreContext} from 'app/RootStoreContext';
import {observer} from 'mobx-react-lite';
import {TableAccounts} from 'app/ui/TableAccounts';
import {RequestService} from 'app/api/RequestService';
import {endpoints} from 'app/api/endpoints';


export const AccountsView = observer(() => {
    const {accounts, usersStore} = useContext(RootStoreContext);

    useEffect(() => {
        accounts.fetchItems();
        usersStore.fetchItems()
    }, [accounts, usersStore]);

    const credit = accounts.items.reduce((sum, current) => {
        return sum + current.credit;
    }, 0);

    const debit = accounts.items.reduce((sum, current) => {
        return sum + current.debit;
    }, 0);

    const surplus = accounts.items.reduce((sum, current) => {
        return sum + current.surplus;
    }, 0);

    const handleAndDay = () =>{
        RequestService.post(endpoints.closeDay,{}).finally(()=>{
            accounts.fetchItems();
            usersStore.fetchItems()
        })
    }
    return (
        <Row>
            <Col span={24}>
                <TableAccounts users={usersStore.items} items={accounts.items}/>
            </Col>
            <Col span={24}>
                Credit: {credit}
            </Col>
            <Col span={24}>
                Debit: {debit}
            </Col>
            <Col span={24}>
                Surplus: {surplus}
            </Col>
            <Col span={24}>
                <Button danger onClick={handleAndDay}>
                    End day
                </Button>
            </Col>
        </Row>
    )
})