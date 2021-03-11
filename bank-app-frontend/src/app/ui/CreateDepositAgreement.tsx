import React from 'react';
import {DepositAgreementForm} from 'app/ui/form/DepositAgreementForm';
import {RouteComponentProps} from 'react-router';
import {MatchId} from 'app/ui/EditUserComponent';
import {RequestService} from 'app/api/RequestService';
import {endpoints} from 'app/api/endpoints';


export const CreateDepositAgreement = (props: RouteComponentProps<MatchId>) => {
    const id = props.match.params.id;

    const onCreate = (data: any) => {
        if (id) {
            RequestService.post(endpoints.createDep(id), {}, data).then(() => {
                props.history.goBack();
            })
        }
    }
    return (
        <>
            <DepositAgreementForm onCreate={onCreate}/>
        </>
    )
}