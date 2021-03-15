import React, {useContext, useEffect, useState} from 'react';
import {Col, Row, Select} from 'antd';
import {TableContract} from 'app/ui/TableContracts';
import {RootStoreContext} from 'app/RootStoreContext';
import {ContractsStore} from 'app/store/ContractsStore';
import {RequestService} from 'app/api/RequestService';
import {endpoints} from 'app/api/endpoints';
import {useHistory} from 'react-router';
import {Routes} from 'app/constants/Routes';


export const ContractsView = () => {
    const {usersStore, contracts} = useContext(RootStoreContext);
    const [contractsStore] = useState(() => new ContractsStore(contracts));
    const history = useHistory();

    useEffect(() => {
        usersStore.fetchItems();
        contracts.fetchItems();
    }, [usersStore, contracts]);

    const handleOnWithdraw = (id : string) =>{
        RequestService.post(endpoints.withdraw(id),{}).finally(()=>{
            contracts.fetchItems();
        });
    }

    const showDataCredit = (id : string) => {
        history.push(Routes.showDateCredit(id));
    }

    const [userId, setUserId] = useState(-1);
    const contractsFiltered: any[] = userId > -1 ? contractsStore.getItemsByUserID(userId) : contractsStore.items;
    return (
        <>
            <Row>
                <Col span={4}>
                    Contracts filter:
                </Col>
                <Col span={20}>
                    <Select style={{minWidth: 300}} onChange={(value) => setUserId(value)} value={userId}>
                        {
                            usersStore.items.map((value) => {
                                return <Select.Option key={value.id}
                                                      value={value.id}>{value.surname} {value.name}</Select.Option>
                            }).concat(<Select.Option key={-1}
                                                     value={-1}>ALL</Select.Option>)
                        }
                    </Select>
                </Col>

            </Row>
            <Row>
                <Col span={24}>
                    <TableContract onShowCreditData={showDataCredit} onWithdraw={handleOnWithdraw} items={contractsFiltered} users={usersStore.items}/>
                </Col>
            </Row>
        </>
    )
}
