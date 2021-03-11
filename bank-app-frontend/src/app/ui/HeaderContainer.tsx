import React from 'react';
import {Header} from 'antd/es/layout/layout';
import {Menu} from 'antd';
import {useHistory, useLocation} from 'react-router';
import {Routes} from 'app/constants/Routes';

export const HeaderContainer = () => {
    const location = useLocation();
    const history = useHistory();
    const goUsers = () => history.push(Routes.users);
    const goCreateUser = () => history.push(Routes.createUser);
    return (
        <Header style={{position: 'fixed', zIndex: 1, width: '100%'}}>
            <div className="logo"/>
            <Menu theme="dark" mode="horizontal" defaultSelectedKeys={[location.pathname]}>
                <Menu.Item key={Routes.users} onClick={goUsers}>Users</Menu.Item>
                <Menu.Item key={Routes.createUser} onClick={goCreateUser}>Create user</Menu.Item>
            </Menu>
        </Header>
    )
}