import React from 'react';
import {Routes} from 'app/constants/Routes';
import {BrowserRouter, Redirect, Route, Switch} from 'react-router-dom';

import {Users} from 'app/ui/Users';
import {EditUserComponent} from 'app/ui/EditUserComponent';

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Switch>
                    <Route exact path={Routes.users} component={Users}/>
                    <Route exact path={Routes.users + '/:id'} component={EditUserComponent}/>
                    <Redirect to={Routes.users}/>
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;
