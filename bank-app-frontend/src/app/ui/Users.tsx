import React, {useContext, useEffect, useState} from 'react';
import {observer} from 'mobx-react-lite';
import {RootStoreContext} from 'app/RootStoreContext';
import {UserComponent} from 'app/ui/UserComponent';
import {useHistory} from 'react-router';
import {Routes} from 'app/constants/Routes';
import {ContractsStore} from 'app/store/ContractsStore';
import {Col, Row, Select, Space} from 'antd';
import {TableContract} from 'app/ui/TableContracts';

export const Users = observer(() => {

    const {usersStore, contracts} = useContext(RootStoreContext);
    const [contractsStore] = useState(() => new ContractsStore(contracts));

    const [userId, setUserId] = useState(-1);

    useEffect(() => {
        usersStore.fetchItems();
        contracts.fetchItems();
    }, [usersStore])

    const history = useHistory();

    const handleEdit = (id: string) => {
        history.push(Routes.editUser(id));
    }

    const handleDelete = (id: string) => {
        usersStore.delete(id).then(() => {
            usersStore.fetchItems();
            contracts.fetchItems();
        });
    }

    const handleCreateDep = (id: string) => {
        history.push(Routes.createDepositAgreement(id));
    }

    const handleCreateCred = (id: string) => {
        history.push(Routes.createCreditAgreement(id));
    }


    return (
        <div className="site-layout-background" style={{padding: 24}}>
            <Row>
                <Col span={24}>
                    <Space wrap={true} direction={'vertical'}>
                    {usersStore.items.map((item) => {
                        return <UserComponent onCreateCredit={handleCreateCred} onCreateDep={handleCreateDep}
                                              key={item.id}
                                              user={item} onDelete={handleDelete}
                                                     onEdit={handleEdit}/>
                    })}
                    </Space>
                </Col>
            </Row>
        </div>
    )
})