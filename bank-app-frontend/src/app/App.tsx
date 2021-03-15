import React from 'react';
import {Routes} from 'app/constants/Routes';
import {BrowserRouter, Redirect, Route, Switch} from 'react-router-dom';

import {Users} from 'app/ui/Users';
import {EditUserComponent} from 'app/ui/EditUserComponent';
import {HeaderContainer} from 'app/ui/HeaderContainer';
import {Col, Layout, Row} from 'antd';
import {Content} from 'antd/es/layout/layout';
import {CreateUSerForm} from 'app/ui/CreateUserForm';
import {CreateDepositAgreement} from 'app/ui/CreateDepositAgreement';
import {CreateCreditAgreement} from 'app/ui/CreateCreditAgreement';
import {ContractsView} from 'app/ui/ContractsView';
import {AccountsView} from 'app/ui/AccountsView';
import {CreditDataView} from 'app/ui/CreditDataView';

function App() {
    return (
        <div className="App">
            <Layout>
                <BrowserRouter>
                    <HeaderContainer/>
                    <Content style={{padding: '0 50px', marginTop: 64}}>
                        <Row>
                            <Col span={24}>
                                <Switch>
                                    <Route exact path={Routes.users} component={Users}/>
                                    <Route exact path={Routes.users + '/edit/:id'} component={EditUserComponent}/>
                                    <Route exact path={Routes.createUser} component={CreateUSerForm}/>
                                    <Route exact path={Routes.contracts} component={ContractsView}/>
                                    <Route exact path={Routes.accounts} component={AccountsView}/>
                                    <Route exact path={Routes.createDepositAgreement(':id')}
                                           component={CreateDepositAgreement}/>
                                    <Route exact path={Routes.createCreditAgreement(':id')}
                                           component={CreateCreditAgreement}/>
                                    <Route exact path={Routes.showDateCredit(':id')}
                                           component={CreditDataView}/>
                                    <Redirect to={Routes.users}/>
                                </Switch>
                            </Col>
                        </Row>
                    </Content>
                </BrowserRouter>
            </Layout>
        </div>
    );
}

export default App;
