import React, {useContext, useEffect, useState} from 'react';
import {Button, Col, DatePicker, Row, Typography} from 'antd';
import {RootStoreContext} from 'app/RootStoreContext';
import {observer} from 'mobx-react-lite';
import {TableAccounts} from 'app/ui/TableAccounts';
import {RequestService} from 'app/api/RequestService';
import {endpoints} from 'app/api/endpoints';
import moment from 'moment';


export const AccountsView = observer(() => {
    const {accounts, usersStore} = useContext(RootStoreContext);

    const [closeDateTime, setCloseDateTime] = useState<moment.Moment | null>(moment());
    const [date, setDate] = useState<moment.Moment>(moment());

    useEffect(() => {
        updateCloseRuleDate();
    }, []);

    const updateCloseRuleDate = () => {
        RequestService.get(endpoints.closeDayDate).then((res) => {
            setDate(moment(res.data.closeDate));
        })
    }

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

    const handleAndDay = () => {
        RequestService.post(endpoints.closeDay, {}, {closeDateTime: closeDateTime?.parseZone().toDate()}).finally(() => {
            accounts.fetchItems();
            usersStore.fetchItems();

        }).then(updateCloseRuleDate)

    }
    const getError = () => {
        if (closeDateTime) {
            if (closeDateTime.isBefore(date, 'd')) {
                return `Please enter not less  ${date.format('YYYY-MM-DD')}`;
            } else {
                return '';
            }
        } else {
            return 'Enter date pls!';
        }

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
                <Button disabled={!!getError()} danger onClick={handleAndDay}>
                    End day
                </Button>
            </Col>
            <Col>
                <Typography style={{color: 'red'}}>{getError()}</Typography>
            </Col>
            <Col span={24}>
                <DatePicker value={closeDateTime} onChange={(value) => setCloseDateTime(value)}/>
            </Col>
        </Row>
    )
})