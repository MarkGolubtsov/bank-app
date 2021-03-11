import React from 'react';
import {RouteComponentProps} from 'react-router';
import {MatchId} from 'app/ui/EditUserComponent';
import {RequestService} from 'app/api/RequestService';
import {endpoints} from 'app/api/endpoints';
import {CreditAgreementForm} from 'app/ui/form/CreditAgreementForm';


export const CreateCreditAgreement = (props: RouteComponentProps<MatchId>) => {
    const id = props.match.params.id;

    const onCreate = (data: any) => {
        if (id) {
            RequestService.post(endpoints.createCredit(id), {}, data).then(() => {
                props.history.goBack();
            })
        }
    }
    return (
        <>
            <CreditAgreementForm onCreate={onCreate}/>
        </>
    )
}